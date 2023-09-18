package com.example.pcserviceapi.services;

import com.example.pcserviceapi.models.Order;
import com.example.pcserviceapi.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends GenericService<Order>{
    public OrderService(OrderRepository repository) {
        super(repository);
    }
}
