package com.example.pcserviceapi.controllers;

import com.example.pcserviceapi.models.OrderType;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/ordertypes")
public class OrderTypeController extends GenericController<OrderType>{

    public OrderTypeController(GenericRepository<OrderType> repository) {
        super(repository);
    }
}
