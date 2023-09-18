package com.example.pcserviceapi.services;

import com.example.pcserviceapi.models.Role;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.RoleRepository;
import com.example.pcserviceapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends GenericService<Role>{
    public RoleService(RoleRepository repository) {
        super(repository);
    }
}
