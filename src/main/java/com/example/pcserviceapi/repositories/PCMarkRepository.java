package com.example.pcserviceapi.repositories;

import com.example.pcserviceapi.models.PCMark;
import org.springframework.stereotype.Repository;

@Repository
public interface PCMarkRepository extends GenericRepository<PCMark>{
    PCMark findByName(String name);
}
