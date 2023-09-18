package com.example.pcserviceapi.controllers;

import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.RoleRepository;
import com.example.pcserviceapi.repositories.UserRepository;
import com.example.pcserviceapi.requestmodels.RegistrationBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public/registration")
public class RegistrationController {
    @Autowired
    private UserRepository userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping()
    private ResponseEntity<String> reg(@RequestBody RegistrationBody registrationBody){
        User userFromDB = userService.findByUsername(registrationBody.getUsername());
        if (userFromDB != null){
            return ResponseEntity.badRequest().body("Пользователь с таким логином уже существует");
        }

        User user = new User();
        user.setActive(true);
        user.setRole(roleRepository.findByName("USER"));
        user.setUsername(registrationBody.getUsername());
        user.setPassword(passwordEncoder.encode(registrationBody.getPassword()));
        user.setFIO(registrationBody.getFio());
        userService.save(user);
        return ResponseEntity.ok("success");
    }
}
