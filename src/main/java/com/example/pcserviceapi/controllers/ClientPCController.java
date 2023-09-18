package com.example.pcserviceapi.controllers;

import com.example.pcserviceapi.models.ClientPC;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/clientpcs")
public class ClientPCController extends GenericController<ClientPC>{

    public ClientPCController(GenericRepository<ClientPC> repository) {
        super(repository);
    }
}
