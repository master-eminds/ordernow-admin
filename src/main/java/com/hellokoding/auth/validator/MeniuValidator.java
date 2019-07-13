package com.hellokoding.auth.validator;

import com.hellokoding.auth.model.Meniu;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MeniuValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Meniu.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Meniu meniu = (Meniu) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denumire", "NotEmpty");

        if (meniu.getStare().equals("0")) {
            errors.rejectValue("stare", "Select.meniuForm.stare");
        }
        if (meniu.getImage()==null||meniu.getImage().length==0|| meniu.getImage().toString().isEmpty()) {
            errors.rejectValue("image", "Empty.meniuForm.image");
        }
    }
}

