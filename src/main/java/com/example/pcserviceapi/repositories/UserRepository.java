package com.example.pcserviceapi.repositories;

import com.example.pcserviceapi.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User>{
    User findByUsername(String username);
}
