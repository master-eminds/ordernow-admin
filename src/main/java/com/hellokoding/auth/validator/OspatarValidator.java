package com.hellokoding.auth.validator;

import com.hellokoding.auth.model.Ospatar;
import com.hellokoding.auth.service.OspatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OspatarValidator implements Validator {
    @Autowired
    private OspatarService ospatarService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Ospatar.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Ospatar ospatar = (Ospatar) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nume", "NotEmpty");

        if (ospatar.getEmail().length() < 6 || ospatar.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.ospatarForm.email");
        }
        if (ospatarService.findById(ospatar.getId()) != null) {
            errors.rejectValue("email", "Duplicate.ospatarForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parola", "NotEmpty");
        if (ospatar.getParola().length() < 4 || ospatar.getParola().length() > 32) {
            errors.rejectValue("parola", "Size.ospatarForm.parola");
        }

    }
}
