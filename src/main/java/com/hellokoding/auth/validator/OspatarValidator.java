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

        if (ospatar.getId() == null) {

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nume", "NotEmpty");

            if (ospatarService.findByEmail(ospatar.getEmail()) != null) {
                errors.rejectValue("email", "Duplicate.ospatarForm.email");
            }


            if (ospatar.getParola().length() < 4 || ospatar.getParola().length() > 32) {
                errors.rejectValue("parola", "Size.ospatarForm.parola");
            }

            if (ospatar.getPasswordConfirm().length() < 4 || ospatar.getParola().length() > 32) {
                errors.rejectValue("passwordConfirm", "Size.ospatarForm.passwordConfirm");
            }

            if (!ospatar.getPasswordConfirm().equals(ospatar.getParola())) {
                errors.rejectValue("parola", "Diff.ospatarForm.passwordConfirm");
                errors.rejectValue("passwordConfirm", "Diff.ospatarForm.passwordConfirm");
            }
        }

        if (ospatar.getId() != null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nume", "NotEmpty");

                if (ospatar.getParola().length() < 4 || ospatar.getParola().length() > 32) {
                    errors.rejectValue("parola", "Size.ospatarForm.parola");
                }


                if (ospatar.getPasswordConfirm().length() < 4 || ospatar.getParola().length() > 32) {
                    errors.rejectValue("passwordConfirm", "Size.ospatarForm.passwordConfirm");
                }

                if (!ospatar.getPasswordConfirm().equals(ospatar.getParola())) {
                    errors.rejectValue("parola", "Diff.ospatarForm.passwordConfirm");
                    errors.rejectValue("passwordConfirm", "Diff.ospatarForm.passwordConfirm");
                }

        }

    }
}
