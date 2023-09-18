package com.example.pcserviceapi.repositories;

import com.example.pcserviceapi.models.PCType;
import org.springframework.stereotype.Repository;

@Repository
public interface PCTypeRepository extends GenericRepository<PCType>{
    PCType findByName(String name);
}
