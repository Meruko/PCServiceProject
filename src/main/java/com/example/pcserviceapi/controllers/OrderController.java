package com.example.pcserviceapi.controllers;

import com.example.pcserviceapi.models.Order;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/orders")
public class OrderController extends GenericController<Order>{

    public OrderController(GenericRepository<Order> repository) {
        super(repository);
    }
}
