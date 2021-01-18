package com.example.registrationApp.domain.exception.client;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException{
    private int code;

    public ClientException(String message, int code){
        super(message);
        this.code = code;
    }
}
