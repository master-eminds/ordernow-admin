package com.hellokoding.auth.validator;

import com.hellokoding.auth.model.Mesaj;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MesajValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Mesaj.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Mesaj mesaj = (Mesaj) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "continutRaspuns", "MesajGol");

    }
}

