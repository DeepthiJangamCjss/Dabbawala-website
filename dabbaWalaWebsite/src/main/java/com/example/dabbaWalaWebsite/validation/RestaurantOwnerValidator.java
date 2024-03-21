package com.example.dabbaWalaWebsite.validation;

import com.example.dabbaWalaWebsite.models.RestaurantOwnerModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RestaurantOwnerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RestaurantOwnerModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RestaurantOwnerModel restaurantOwnerModel=(RestaurantOwnerModel) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "restaurantOwner.username", "Username should not be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "restaurantOwner.password.empty", "Password should not be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"ownerName","restaurantOwner.ownerName","restaurant Owner name should not be blank");

        String ownerUsername = restaurantOwnerModel.getUsername();
        if(restaurantOwnerModel.getUsername()!=null && restaurantOwnerModel.getUsername().length()< 5){
            errors.rejectValue("username","restaurantOwner.username.size","username should be atleast 5 characters");
        }

        if(restaurantOwnerModel.getOwnerName()!=null && restaurantOwnerModel.getOwnerName().length()< 5){
            errors.rejectValue("ownerName","restaurantOwner.ownerName.size","name should be atleast 5 characters");
        }
        if(!restaurantOwnerModel.getEmail().endsWith("@gmail.com")){
            errors.rejectValue("email","restaurantOwner.email","Invalid email format");
        }
    }
}
