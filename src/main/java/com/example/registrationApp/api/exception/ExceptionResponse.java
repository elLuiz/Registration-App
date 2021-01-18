package com.example.registrationApp.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private int status;
    private OffsetDateTime date;
    private String message;
}
