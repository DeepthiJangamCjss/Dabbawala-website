package com.example.dabbaWalaWebsite.validation;


import com.example.dabbaWalaWebsite.models.CustomerModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Set;
@Component
public class CustomerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerModel customerModel=(CustomerModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "customer.username", "Username should not be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "customer.password.empty", "Password should not be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","customer.name","customer name should not be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"accountBalance","customer.balance");
        
        if(customerModel.getUsername()!=null && customerModel.getName().length()< 5){
           errors.rejectValue("username","customer.username.size","username should be atleast 5 characters");
        }

        if(customerModel.getName()!=null && customerModel.getName().length()< 5){
            errors.rejectValue("name","customer.name.size","name should be atleast 5 characters");
        }

        if (customerModel.getAge() < 18) {
            errors.rejectValue("age", "customer.age.invalid", "Age must be at least 18");
        }
        if(!customerModel.getEmail().endsWith("@gmail.com")){
            errors.rejectValue("email","customer.email","Invalid email format");
        }
    }
}
