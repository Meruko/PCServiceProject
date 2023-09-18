package com.example.pcserviceapi.services;

import com.example.pcserviceapi.models.OrderStatus;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.OrderStatusRepository;
import com.example.pcserviceapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService extends GenericService<OrderStatus>{
    public OrderStatusService(OrderStatusRepository repository) {
        super(repository);
    }
}
