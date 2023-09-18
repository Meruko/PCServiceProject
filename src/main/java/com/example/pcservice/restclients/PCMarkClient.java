package com.example.pcservice.restclients;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.api.GenericRestClient;
import com.example.pcservice.models.PCMark;
import com.example.pcservice.models.User;
import com.example.pcservice.summaries.PCMarkSummary;
import com.example.pcservice.summaries.PCTypeSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PCMarkClient extends GenericRestClient<PCMark, PCMarkSummary> {
    public PCMarkClient() {
        super(ApiHelper.getPcMarksUrl());
    }

    @Override
    public List<PCMark> findAll(String url) {
        ResponseEntity<PCMarkSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PCMarkSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public PCMark findById(String url, long id) {
        ResponseEntity<PCMark> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PCMark>() {});
        return response.getBody();
    }

    @Override
    public PCMark create(String url, PCMark entity){
        ResponseEntity<PCMark> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<PCMark>() {});

        return response.getBody();
    }

    @Override
    public PCMark update(String url, PCMark entity){
        ResponseEntity<PCMark> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<PCMark>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<PCMark> findAll() {
        ResponseEntity<PCMarkSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PCMarkSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public PCMark findById(long id) {
        ResponseEntity<PCMark> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PCMark>() {});
        return response.getBody();
    }

    @Override
    public PCMark create(PCMark entity){
        ResponseEntity<PCMark> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<PCMark>() {});

        return response.getBody();
    }

    @Override
    public PCMark update(PCMark entity){
        ResponseEntity<PCMark> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<PCMark>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
