package com.example.registrationApp.domain.exception.server;

public class EmailNotSentException extends ServerException{
    public EmailNotSentException(String message, int code) {
        super(message, code);
    }
}
