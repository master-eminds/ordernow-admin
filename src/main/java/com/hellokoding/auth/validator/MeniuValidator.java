package com.hellokoding.auth.validator;

import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.service.MeniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeniuValidator implements Validator {
    @Autowired
    private MeniuService meniuService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Meniu.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Meniu meniu = (Meniu) o;
    }
}
