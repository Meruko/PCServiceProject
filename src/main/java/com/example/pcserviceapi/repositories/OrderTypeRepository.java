package com.example.pcserviceapi.repositories;

import com.example.pcserviceapi.models.OrderType;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTypeRepository extends GenericRepository<OrderType>{
    OrderType findByName(String name);
}
