package com.example.pcserviceapi.services;

import com.example.pcserviceapi.models.OrderType;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.OrderTypeRepository;
import com.example.pcserviceapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderTypeService extends GenericService<OrderType>{
    public OrderTypeService(OrderTypeRepository repository) {
        super(repository);
    }
}
