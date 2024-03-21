package com.example.dabbaWalaWebsite.controller;

import com.example.dabbaWalaWebsite.models.CustomerModel;
import com.example.dabbaWalaWebsite.models.RestaurantOwnerModel;
import com.example.dabbaWalaWebsite.service.AdminService;
import com.example.dabbaWalaWebsite.service.CustomerService;
import com.example.dabbaWalaWebsite.service.RestaurantOwnerService;
import com.example.dabbaWalaWebsite.validation.CustomerValidator;
import com.example.dabbaWalaWebsite.validation.RestaurantOwnerValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
class MainController{
    @Autowired
    private CustomerValidator customerValidator;
    @Autowired
    private RestaurantOwnerValidator restaurantOwnerValidator;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RestaurantOwnerService restaurantOwnerService;
    @RequestMapping("/")
    public String homePage() {
        return "home";
    }
    @RequestMapping("/customerRegister")
    public String customerRegister(Model model) {
        model.addAttribute("customerModel", new CustomerModel());
        return "customerRegister";
    }

    @RequestMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customerModel") @Valid CustomerModel customerModel, BindingResult bindingResult,Model model){
        customerValidator.validate(customerModel,bindingResult);
        if(bindingResult.hasErrors()){
            return "customerRegister";
        }
        boolean isUserSaved = customerService.addCustomer(customerModel);
        if(isUserSaved){
            return "redirect:/customer/customerLoginForm";
        }
        return "customerRegister";
    }
    @RequestMapping("/customerLoginForm")
    public String customerLoginForm(){
        return "customerLogin";
    }

    @RequestMapping("/restaurantOwnerRegister")
    public String restaurantOwnerRegister(Model model) {
        model.addAttribute("restaurantOwnerModel", new RestaurantOwnerModel());
        return "restaurantOwnerRegister";
    }
    @RequestMapping("/saveRestaurantOwner")
    public String saveRestaurantOwner(@ModelAttribute("restaurantOwnerModel") @Valid RestaurantOwnerModel restaurantOwnerModel, BindingResult bindingResult,Model model){
        restaurantOwnerValidator.validate(restaurantOwnerModel,bindingResult);
        if(bindingResult.hasErrors()){
            return "restaurantOwnerRegister";
        }
        System.out.println(restaurantOwnerModel);
        boolean isRestaurantOwnerSaved = restaurantOwnerService.addRestaurantOwner(restaurantOwnerModel);
        if(isRestaurantOwnerSaved){
            return "redirect:/restaurant/restaurantOwnerLoginForm";
        }
        return "restaurantOwnerRegister";
    }
    @RequestMapping("/restaurantOwnerLoginForm")
    public String restaurantOwnerLoginForm(){
        return "restaurantOwnerLogin";
    }
    @RequestMapping("/restaurantOwnerLogin")
    public String restaurantOwnerLogin(@RequestParam("username") String username,@RequestParam("password") String password,Model model){
        boolean isAuthenticated = restaurantOwnerService.authenticateRestauratOwner(username, password);
        if (isAuthenticated) {
            RestaurantOwnerModel restaurantOwnerModel= restaurantOwnerService.getRestaurantOwnerByUsername(username);
            model.addAttribute("restaurantOwnerModel",restaurantOwnerModel);
            return "restaurantOwnerDashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "restaurantOwnerLogin";
        }
    }
}