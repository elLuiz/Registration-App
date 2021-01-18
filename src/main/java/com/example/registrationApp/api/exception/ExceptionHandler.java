package com.example.registrationApp.api.exception;

import com.example.registrationApp.domain.exception.client.ClientException;
import com.example.registrationApp.domain.exception.server.ServerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handleClientException(ClientException clientException, WebRequest request){
        HttpStatus httpStatus = HttpStatus.resolve(clientException.getCode());
        ExceptionResponse exceptionResponse = createExceptionResponse(clientException.getMessage(), clientException.getCode());
        return handleExceptionInternal(clientException, exceptionResponse, new HttpHeaders(), httpStatus, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ServerException.class)
    public ResponseEntity<Object> handleServerException(ServerException serverException, WebRequest webRequest){
        HttpStatus httpStatus = HttpStatus.resolve(serverException.getCode());
        ExceptionResponse exceptionResponse = createExceptionResponse(serverException.getMessage(), serverException.getCode());
        return  handleExceptionInternal(serverException, exceptionResponse, new HttpHeaders(), httpStatus, webRequest);
    }

    private ExceptionResponse createExceptionResponse(String message, int statusCode){
        ExceptionResponse exceptionResponse = new ExceptionResponse(statusCode, OffsetDateTime.now(), message);
        return exceptionResponse;
    }
}
