package com.example.pcservice.restclients;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.api.GenericRestClient;
import com.example.pcservice.models.Role;
import com.example.pcservice.models.User;
import com.example.pcservice.summaries.RoleSummary;
import com.example.pcservice.summaries.UserSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RoleClient extends GenericRestClient<Role, RoleSummary> {
    public RoleClient() {
        super(ApiHelper.getRolesUrl());
    }

    @Override
    public List<Role> findAll(String url) {
        ResponseEntity<RoleSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<RoleSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public Role findById(String url, long id) {
        ResponseEntity<Role> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Role>() {});
        return response.getBody();
    }

    @Override
    public Role create(String url, Role entity){
        ResponseEntity<Role> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Role>() {});

        return response.getBody();
    }

    @Override
    public Role update(String url, Role entity){
        ResponseEntity<Role> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Role>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<Role> findAll() {
        ResponseEntity<RoleSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<RoleSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public Role findById(long id) {
        ResponseEntity<Role> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Role>() {});
        return response.getBody();
    }

    @Override
    public Role create(Role entity){
        ResponseEntity<Role> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Role>() {});

        return response.getBody();
    }

    @Override
    public Role update(Role entity){
        ResponseEntity<Role> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<Role>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
