package com.example.pcserviceapi.controllers;

import com.example.pcserviceapi.models.Role;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/roles")
public class RoleController extends GenericController<Role>{

    public RoleController(GenericRepository<Role> repository) {
        super(repository);
    }
}
