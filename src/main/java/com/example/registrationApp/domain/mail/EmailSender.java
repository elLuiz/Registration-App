package com.example.registrationApp.domain.mail;

public interface EmailSender {
    void send(String to, String email);
}
