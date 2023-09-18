package com.example.pcservice.summaries;

import com.example.pcservice.models.OrderType;
import com.example.pcservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderTypeSummary extends AbstractSummary<OrderType> {
}
