package com.example.pcservice.restclients;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.api.GenericRestClient;
import com.example.pcservice.models.Order;
import com.example.pcservice.models.User;
import com.example.pcservice.summaries.OrderStatusSummary;
import com.example.pcservice.summaries.OrderSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class OrderClient extends GenericRestClient<Order, OrderSummary> {
    public OrderClient() {
        super(ApiHelper.getOrdersUrl());
    }

    @Override
    public List<Order> findAll(String url) {
        ResponseEntity<OrderSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<OrderSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public Order findById(String url, long id) {
        ResponseEntity<Order> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Order>() {});
        return response.getBody();
    }

    @Override
    public Order create(String url, Order entity){
        ResponseEntity<Order> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Order>() {});

        return response.getBody();
    }

    @Override
    public Order update(String url, Order entity){
        ResponseEntity<Order> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Order>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<Order> findAll() {
        ResponseEntity<OrderSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<OrderSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public Order findById(long id) {
        ResponseEntity<Order> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Order>() {});
        return response.getBody();
    }

    @Override
    public Order create(Order entity){
        ResponseEntity<Order> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Order>() {});

        return response.getBody();
    }

    @Override
    public Order update(Order entity){
        ResponseEntity<Order> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Order>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
