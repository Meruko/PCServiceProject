package com.example.pcserviceapi.repositories;

import com.example.pcserviceapi.models.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericRepository<Role>{
    Role findByName(String name);
}
