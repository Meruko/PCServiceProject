package com.example.pcserviceapi.controllers;

import com.example.pcserviceapi.models.PCMark;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/pcmarks")
public class PCMarkController extends GenericController<PCMark>{

    public PCMarkController(GenericRepository<PCMark> repository) {
        super(repository);
    }
}
