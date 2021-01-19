package com.example.registrationApp.api.utils;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {
    private final static String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    @Override
    public boolean test(String email) {
        if(isValidEmail(email))
            return true;

        return false;
    }

    private boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
            return true;
        return false;
    }
}
