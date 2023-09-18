package com.example.pcserviceapi.services;

import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User>{
    public UserService(UserRepository repository) {
        super(repository);
    }
}
