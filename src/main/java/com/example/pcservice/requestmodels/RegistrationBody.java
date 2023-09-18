package com.example.pcservice.requestmodels;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationBody {
    @NotBlank(message = "Login is required")
    @Size(min = 3, max = 32, message = "Size must be between 3 and 32!")
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 3, max = 255, message = "Size must be between 3 and 255!")
    private String password;
    @NotBlank(message = "FIO is required")
    @Size(min = 3, max = 200, message = "Size must be between 3 and 255!")
    private String fio;
}
