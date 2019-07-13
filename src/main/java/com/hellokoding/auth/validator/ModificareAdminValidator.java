package com.hellokoding.auth.validator;

import com.hellokoding.auth.model.Admin;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ModificareAdminValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Admin.class.equals(aClass);
    }

   @Override
    public void validate(Object o, Errors errors) {
        Admin admin = (Admin) o;
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nume", "NotEmpty");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenume", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (admin.getUsername().length() < 6 || admin.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.adminForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (admin.getPassword().length() < 8 || admin.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.adminForm.password");
        }
        if (!admin.getPasswordConfirm().equals(admin.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.adminForm.passwordConfirm");
        }
    }
}
