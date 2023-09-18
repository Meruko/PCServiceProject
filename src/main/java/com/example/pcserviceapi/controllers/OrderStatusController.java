package com.example.pcserviceapi.controllers;

import com.example.pcserviceapi.models.OrderStatus;
import com.example.pcserviceapi.models.User;
import com.example.pcserviceapi.repositories.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/orderstatuses")
public class OrderStatusController extends GenericController<OrderStatus>{

    public OrderStatusController(GenericRepository<OrderStatus> repository) {
        super(repository);
    }
}
