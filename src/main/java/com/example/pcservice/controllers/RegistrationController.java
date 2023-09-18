package com.example.pcservice.controllers;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.api.GenericRestClient;
import com.example.pcservice.models.User;
import com.example.pcservice.models.Role;
import com.example.pcservice.requestmodels.RegistrationBody;
import com.example.pcservice.restclients.RoleClient;
import com.example.pcservice.restclients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {
    private final UserClient userClient = new UserClient();
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/registration")
    private String registration(@ModelAttribute("reg") RegistrationBody registrationBody, Model model){
        model.addAttribute("reg", registrationBody);
        return "registration";
    }

    @PostMapping("/registration")
    private String reg(Model model, @Valid RegistrationBody registrationBody, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("reg", registrationBody);
            return "registration";
        }

        User userFromDB = userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(registrationBody.getUsername())).findFirst().orElse(null);
        if (userFromDB != null){
            model.addAttribute("reg", registrationBody);
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "registration";
        }
        String regUrl = "http://localhost:8081/api/public/registration";
        ResponseEntity<String> response = restTemplate.exchange(
                regUrl,
                HttpMethod.POST,
                new HttpEntity<>(registrationBody),
                String.class
        );
        if (response.getBody().equals("success"))
            return "redirect:/login";
        else{
            model.addAttribute("reg", registrationBody);
            model.addAttribute("message", "Ошибка регистрации");
            return "registration";
        }
    }
}
