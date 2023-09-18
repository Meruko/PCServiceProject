package com.example.pcserviceapi.repositories;

import com.example.pcserviceapi.models.OrderStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends GenericRepository<OrderStatus>{
    OrderStatus findByName(String name);
}
