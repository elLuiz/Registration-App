package com.example.registrationApp.domain.exception.client;

import com.example.registrationApp.domain.exception.client.ClientException;

public class EmailNotFoundException extends ClientException {
    public EmailNotFoundException(String message, int code){
        super(message, code);
    }
}
