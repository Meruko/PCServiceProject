package com.example.pcservice.restclients;

import com.example.pcservice.api.ApiHelper;
import com.example.pcservice.api.GenericRestClient;
import com.example.pcservice.models.ClientPC;
import com.example.pcservice.models.User;
import com.example.pcservice.summaries.ClientPCSummary;
import com.example.pcservice.summaries.OrderSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ClientPCClient extends GenericRestClient<ClientPC, ClientPCSummary> {
    public ClientPCClient() {
        super(ApiHelper.getClientPcsUrl());
    }

    @Override
    public List<ClientPC> findAll(String url) {
        ResponseEntity<ClientPCSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ClientPCSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public ClientPC findById(String url, long id) {
        ResponseEntity<ClientPC> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ClientPC>() {});
        return response.getBody();
    }

    @Override
    public ClientPC create(String url, ClientPC entity){
        ResponseEntity<ClientPC> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<ClientPC>() {});

        return response.getBody();
    }

    @Override
    public ClientPC update(String url, ClientPC entity){
        ResponseEntity<ClientPC> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<ClientPC>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<ClientPC> findAll() {
        ResponseEntity<ClientPCSummary> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ClientPCSummary>() {});
        return response.getBody().getContent();
    }

    @Override
    public ClientPC findById(long id) {
        ResponseEntity<ClientPC> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ClientPC>() {});
        return response.getBody();
    }

    @Override
    public ClientPC create(ClientPC entity){
        ResponseEntity<ClientPC> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<ClientPC>() {});

        return response.getBody();
    }

    @Override
    public ClientPC update(ClientPC entity){
        ResponseEntity<ClientPC> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<ClientPC>() {});

        return response.getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}
