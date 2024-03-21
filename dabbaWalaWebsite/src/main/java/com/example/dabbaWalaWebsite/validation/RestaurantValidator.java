package com.example.dabbaWalaWebsite.validation;

import com.example.dabbaWalaWebsite.models.RestaurantModel;
import com.example.dabbaWalaWebsite.models.RestaurantOwnerModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class RestaurantValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RestaurantModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RestaurantModel restaurantModel=(RestaurantModel) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "restaurantName", "restaurantModel.restaurantName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "street");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "state");
    }
}
