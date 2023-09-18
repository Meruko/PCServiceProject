package com.example.pcservice.restclients;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.api.GenericRestClient;
import com.example.pcservice.models.OrderStatus;
import com.example.pcservice.models.User;
import com.example.pcservice.summaries.OrderStatusSummary;
import com.example.pcservice.summaries.OrderTypeSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class OrderStatusClient extends GenericRestClient<OrderStatus, OrderStatusSummary> {
    public OrderStatusClient() {
        super(ApiHelper.getOrderStatusesUrl());
    }

    @Override
    public List<OrderStatus> findAll(String url) {
        ResponseEntity<OrderStatusSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<OrderStatusSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public OrderStatus findById(String url, long id) {
        ResponseEntity<OrderStatus> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<OrderStatus>() {});
        return response.getBody();
    }

    @Override
    public OrderStatus create(String url, OrderStatus entity){
        ResponseEntity<OrderStatus> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<OrderStatus>() {});

        return response.getBody();
    }

    @Override
    public OrderStatus update(String url, OrderStatus entity){
        ResponseEntity<OrderStatus> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<OrderStatus>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<OrderStatus> findAll() {
        ResponseEntity<OrderStatusSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<OrderStatusSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public OrderStatus findById(long id) {
        ResponseEntity<OrderStatus> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<OrderStatus>() {});
        return response.getBody();
    }

    @Override
    public OrderStatus create(OrderStatus entity){
        ResponseEntity<OrderStatus> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<OrderStatus>() {});

        return response.getBody();
    }

    @Override
    public OrderStatus update(OrderStatus entity){
        ResponseEntity<OrderStatus> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<OrderStatus>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
