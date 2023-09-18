package com.example.pcserviceapi.services;

import com.example.pcserviceapi.models.PCType;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.PCTypeRepository;
import com.example.pcserviceapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PCTypeService extends GenericService<PCType>{
    public PCTypeService(PCTypeRepository repository) {
        super(repository);
    }
}
