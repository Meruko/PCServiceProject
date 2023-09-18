package com.example.pcservice.summaries;

import com.example.pcservice.models.Order;
import com.example.pcservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderSummary extends AbstractSummary<Order> {
}
