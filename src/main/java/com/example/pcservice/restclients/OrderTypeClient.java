package com.example.pcservice.restclients;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.api.GenericRestClient;
import com.example.pcservice.models.OrderType;
import com.example.pcservice.models.User;
import com.example.pcservice.summaries.OrderTypeSummary;
import com.example.pcservice.summaries.PCMarkSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class OrderTypeClient extends GenericRestClient<OrderType, OrderTypeSummary> {
    public OrderTypeClient() {
        super(ApiHelper.getOrderTypesUrl());
    }

    @Override
    public List<OrderType> findAll(String url) {
        ResponseEntity<OrderTypeSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<OrderTypeSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public OrderType findById(String url, long id) {
        ResponseEntity<OrderType> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<OrderType>() {});
        return response.getBody();
    }

    @Override
    public OrderType create(String url, OrderType entity){
        ResponseEntity<OrderType> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<OrderType>() {});

        return response.getBody();
    }

    @Override
    public OrderType update(String url, OrderType entity){
        ResponseEntity<OrderType> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<OrderType>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<OrderType> findAll() {
        ResponseEntity<OrderTypeSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<OrderTypeSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public OrderType findById(long id) {
        ResponseEntity<OrderType> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<OrderType>() {});
        return response.getBody();
    }

    @Override
    public OrderType create(OrderType entity){
        ResponseEntity<OrderType> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<OrderType>() {});

        return response.getBody();
    }

    @Override
    public OrderType update(OrderType entity){
        ResponseEntity<OrderType> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<OrderType>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
