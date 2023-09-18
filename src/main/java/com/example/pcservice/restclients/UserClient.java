package com.example.pcservice.restclients;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.api.GenericRestClient;
import com.example.pcservice.models.User;
import com.example.pcservice.summaries.UserSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserClient extends GenericRestClient<User, UserSummary> {
    public UserClient() {
        super(ApiHelper.getUsersUrl());
    }

    @Override
    public List<User> findAll(String url) {
        ResponseEntity<UserSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public User findById(String url, long id) {
        ResponseEntity<User> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<User>() {});
        return response.getBody();
    }

    @Override
    public User create(String url, User entity){
        ResponseEntity<User> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<User>() {});

        return response.getBody();
    }

    @Override
    public User update(String url, User entity){
        ResponseEntity<User> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<User>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<User> findAll() {
        ResponseEntity<UserSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public User findById(long id) {
        ResponseEntity<User> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<User>() {});
        return response.getBody();
    }

    @Override
    public User create(User entity){
        ResponseEntity<User> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<User>() {});

        return response.getBody();
    }

    @Override
    public User update(User entity){
        ResponseEntity<User> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<User>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
