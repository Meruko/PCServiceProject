package com.example.pcserviceapi.services;

import com.example.pcserviceapi.models.PCMark;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.PCMarkRepository;
import com.example.pcserviceapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PCMarkService extends GenericService<PCMark>{
    public PCMarkService(PCMarkRepository repository) {
        super(repository);
    }
}
