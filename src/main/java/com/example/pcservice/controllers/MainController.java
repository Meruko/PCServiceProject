package com.example.pcservice.controllers;

import com.example.pcservice.models.User;
import com.example.pcservice.restclients.RoleClient;
import com.example.pcservice.restclients.UserClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    private final UserClient userClient = new UserClient();
    private final RoleClient roleClient = new RoleClient();

    @GetMapping()
    public String index(HttpServletRequest request){
        List<GrantedAuthority> roles = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if (roles.get(0).getAuthority().equals("2"))
            return "redirect:/users";
        else if (roles.get(0).getAuthority().equals("3"))
            return "indexEmp";
        else if (roles.get(0).getAuthority().equals("4"))
            return "indexCrud";

        return "index";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        User user = userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(getUsername())).findFirst().orElseThrow();
        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        User user = userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(getUsername())).findFirst().orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleClient.findAll());
        return "edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") long id, @Valid User user, BindingResult result) {
        if (result.hasErrors()){
            return "edit";
        }

        userClient.update(user);
        return "profile";
    }

    public static String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the username of the currently authenticated user
        String username = authentication.getName();
        return username;
    }
}
