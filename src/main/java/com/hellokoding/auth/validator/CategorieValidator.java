package com.hellokoding.auth.validator;

import com.hellokoding.auth.model.Categorie;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategorieValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Categorie.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Categorie categorie = (Categorie) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "denumire", "NotEmpty");

        if (!categorie.getVizibilitate().equals("vizibil")&&!categorie.getVizibilitate().equals("invizibil")) {
            errors.rejectValue("vizibilitate", "Select.categorieForm.vizibilitate");
        }
        if (categorie.getImagine()==null||categorie.getImagine().length==0|| categorie.getImagine().toString().isEmpty()) {
            errors.rejectValue("imagine", "Empty.meniuForm.image");
        }
    }

}
