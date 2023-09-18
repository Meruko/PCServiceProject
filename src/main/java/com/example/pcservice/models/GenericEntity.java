package com.example.pcservice.models;

public interface GenericEntity<T> {

    // update current instance with provided data
    void update(T source);
    long getId();

    // based on current data create new instance with new id
    T createNewInstance();

}