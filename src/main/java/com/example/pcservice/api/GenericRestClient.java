package com.example.pcservice.api;

import com.example.pcservice.models.GenericEntity;
import com.example.pcservice.models.User;
import com.example.pcservice.summaries.AbstractSummary;
import com.example.pcservice.summaries.UserSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GenericRestClient<T extends GenericEntity<T>, S extends AbstractSummary<T>> {
    public final String url;
    public final RestTemplate restTemplate = new RestTemplate();

    public GenericRestClient(String url) {
        this.url = url;
    }

    public List<T> findAll(String url) {
        ResponseEntity<S> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<S>() {});
        return response.getBody().getContent();
    }

    public T findById(String url, long id) {
        ResponseEntity<T> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<T>() {});
        return response.getBody();
    }

    public T create(String url, T entity){
        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<T>() {});

        return response.getBody();
    }

    public T update(String url, T entity){
        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<T>() {});

        return response.getBody();
    }

    public void deleteById(String url, long id) {
        restTemplate.delete(url + "/" + id);
    }

    public List<T> findAll() {
        ResponseEntity<S> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<S>() {});
        return response.getBody().getContent();
    }

    public T findById(long id) {
        ResponseEntity<T> response = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<T>() {});
        return response.getBody();
    }

    public T create(T entity){
        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<T>() {});

        return response.getBody();
    }

    public T update(T entity){
        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(entity),
                new ParameterizedTypeReference<T>() {});

        return response.getBody();
    }

    public void deleteById(long id) {
        restTemplate.delete(url + "/" + id);
    }
}