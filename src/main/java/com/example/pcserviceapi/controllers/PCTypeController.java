package com.example.pcserviceapi.controllers;

import com.example.pcserviceapi.models.PCType;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/pctypes")
public class PCTypeController extends GenericController<PCType>{

    public PCTypeController(GenericRepository<PCType> repository) {
        super(repository);
    }
}
