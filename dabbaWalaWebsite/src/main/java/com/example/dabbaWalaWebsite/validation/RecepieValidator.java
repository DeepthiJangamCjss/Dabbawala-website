package com.example.dabbaWalaWebsite.validation;

import com.example.dabbaWalaWebsite.models.LocationModel;
import com.example.dabbaWalaWebsite.models.RecepieModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class RecepieValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RecepieModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RecepieModel recepieModel=(RecepieModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "recepieName", "recepie.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "price");
    }
}
