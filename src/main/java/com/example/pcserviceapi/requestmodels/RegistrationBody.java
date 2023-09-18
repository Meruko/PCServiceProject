package com.example.pcserviceapi.requestmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationBody {
    private String username;
    private String password;
    private String fio;
}
