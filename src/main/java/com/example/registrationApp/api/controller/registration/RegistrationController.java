package com.example.registrationApp.api.controller.registration;

import com.example.registrationApp.api.model.RegistrationModel;
import com.example.registrationApp.domain.service.RegistrationService;
import com.example.registrationApp.domain.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/registration")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final TokenService tokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody RegistrationModel registrationModel){
        return registrationService.register(registrationModel);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam String token){
        return tokenService.confirmToken(token);
    }
}
