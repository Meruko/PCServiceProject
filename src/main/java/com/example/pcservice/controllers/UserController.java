package com.example.pcservice.controllers;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.models.Role;
import com.example.pcservice.models.User;
import com.example.pcservice.restclients.RoleClient;
import com.example.pcservice.restclients.UserClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('2')")
public class UserController{
    private final UserClient userClient = new UserClient();
    private final RoleClient roleClient = new RoleClient();
    protected final String name = "user";

    @GetMapping()
    public String index(Model model) {
        List<User> users = userClient.findAll();
        users.removeIf(u -> u.getUsername().equals(MainController.getUsername()));

        model.addAttribute(name+"s", users);
        return name+"/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute(name, userClient.findById(id));
        return name+"/show";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "id", defaultValue = "-1") long id, Model model, HttpServletRequest request) {
        User user = userClient.findById(id);
        if (user.getUsername().equals(request.getRemoteUser()))
            throw new IllegalArgumentException("Invalid "+name+" Id:" + id);

        model.addAttribute(name, user);
        return name+"/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute(name, userClient.findById(id));
        model.addAttribute("roles", roleClient.findAll());
        model.addAttribute("user", userClient.findById(id));
        return name+"/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") long id,
                         @RequestParam("role") long role,
                         @RequestParam("username") String username,
                         @RequestParam("FIO") String FIO) {
        User user = userClient.findById(id);
        user.setRole(roleClient.findById(role));
        user.setFIO(FIO);
        user.setUsername(username);

        userClient.update(user);
        return "redirect:/"+name+"s";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userClient.deleteById(id);
        return "redirect:/"+name+"s";
    }
}
