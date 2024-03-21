package com.example.dabbaWalaWebsite.validation;

import com.example.dabbaWalaWebsite.models.LocationModel;
import com.example.dabbaWalaWebsite.models.RestaurantModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class LocationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LocationModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LocationModel locationModel=(LocationModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "street");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "state");
    }
}
