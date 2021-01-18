package com.example.registrationApp.domain.service;

import com.example.registrationApp.domain.exception.client.ClientException;
import com.example.registrationApp.domain.model.token.Token;
import com.example.registrationApp.domain.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserService userService;

    public void saveToken(Token token){
        token.setCreatedAt(LocalDateTime.now());
        tokenRepository.save(token);
    }

    public String confirmToken(String token){
        Token confirmationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new ClientException("Token not found", 404));
        if(verifyToken(confirmationToken)){
            confirmationToken.setConfirmedAt(LocalDateTime.now());
            userService.enableUser(confirmationToken.getAppUser().getEmail());
            tokenRepository.save(confirmationToken);
        }

        return "Confirmed";
    }

    private boolean verifyToken(Token token){
        if(isTokenConfirmed(token))
            throw new ClientException("Token already confirmed.", 400);
        if(isTokenExpired(token.getExpiresAt()))
            throw new ClientException("This token is out dated.", 400);

        return true;
    }

    private boolean isTokenConfirmed(Token token){
        if(token.getConfirmedAt() != null)
            return true;
        return false;
    }

    private boolean isTokenExpired(LocalDateTime expiredAt){
        if(expiredAt.isBefore(LocalDateTime.now()))
            return true;
        return false;
    }
}
