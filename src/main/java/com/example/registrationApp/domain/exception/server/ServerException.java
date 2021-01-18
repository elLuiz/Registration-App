package com.example.registrationApp.domain.exception.server;

import lombok.Getter;

@Getter
public class ServerException extends RuntimeException{
    private int code;
    public ServerException(String message, int code){
        super(message);
        this.code = code;
    }
}
