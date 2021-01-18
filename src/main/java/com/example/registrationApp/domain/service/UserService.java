package com.example.registrationApp.domain.service;

import com.example.registrationApp.domain.exception.client.ClientException;
import com.example.registrationApp.domain.exception.client.EmailNotFoundException;
import com.example.registrationApp.domain.model.AppUser;
import com.example.registrationApp.domain.model.token.Token;
import com.example.registrationApp.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userExists = verifyUserEmail(email);
        if(!userExists.isPresent())
            throw new EmailNotFoundException("Email not found", 404);

        return userExists.get();
    }

    public String signUpUser(AppUser appUser){
        boolean userExists = verifyUserEmail(appUser.getEmail()).isPresent();
        if(userExists)
            throw new ClientException("User already taken.", 400);

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        userRepository.save(appUser);

        String token = createConfirmationToken(appUser);
        return token;
    }

    public int enableUser(String email){
        return userRepository.enableUser(email);
    }

    private Optional<AppUser> verifyUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private String createConfirmationToken(AppUser appUser){
        String uuidToken = UUID.randomUUID().toString();
        Token token = new Token(
                uuidToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        tokenService.saveToken(token);
        return uuidToken;
    }
}
