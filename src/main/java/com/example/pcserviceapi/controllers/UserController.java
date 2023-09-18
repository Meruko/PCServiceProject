package com.example.pcserviceapi.controllers;

import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.GenericRepository;
import com.example.pcserviceapi.repositories.RoleRepository;
import com.example.pcserviceapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/public/users")
public class UserController extends GenericController<User>{

    public UserController(GenericRepository<User> repository) {
        super(repository);
    }
}
