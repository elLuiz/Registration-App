package com.example.registrationApp.api.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RegistrationModel {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
