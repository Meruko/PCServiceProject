package com.example.pcservice.restclients;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.api.GenericRestClient;
import com.example.pcservice.models.PCType;
import com.example.pcservice.models.User;
import com.example.pcservice.summaries.PCTypeSummary;
import com.example.pcservice.summaries.UserSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PCTypeClient extends GenericRestClient<PCType, PCTypeSummary> {
    public PCTypeClient() {
        super(ApiHelper.getPcTypesUrl());
    }

    @Override
    public List<PCType> findAll(String url) {
        ResponseEntity<PCTypeSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PCTypeSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public PCType findById(String url, long id) {
        ResponseEntity<PCType> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PCType>() {});
        return response.getBody();
    }

    @Override
    public PCType create(String url, PCType entity){
        ResponseEntity<PCType> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<PCType>() {});

        return response.getBody();
    }

    @Override
    public PCType update(String url, PCType entity){
        ResponseEntity<PCType> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<PCType>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<PCType> findAll() {
        ResponseEntity<PCTypeSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PCTypeSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public PCType findById(long id) {
        ResponseEntity<PCType> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PCType>() {});
        return response.getBody();
    }

    @Override
    public PCType create(PCType entity){
        ResponseEntity<PCType> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<PCType>() {});

        return response.getBody();
    }

    @Override
    public PCType update(PCType entity){
        ResponseEntity<PCType> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<PCType>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
