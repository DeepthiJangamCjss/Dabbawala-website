package com.example.dabbaWalaWebsite.controller;

import com.example.dabbaWalaWebsite.conversions.ModelEntityConversions;
import com.example.dabbaWalaWebsite.models.*;
import com.example.dabbaWalaWebsite.serviceImplementations.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ModelEntityConversions converter;
    @Autowired
    private CustomerServiceImpl customerService;
    private CustomerModel customerModel;
    public void setCustomerModelWhenLogin(CustomerModel customerModel){
        this.customerModel=customerModel;
    }
    @RequestMapping("/customerLoginForm")
    public String customerLoginForm(){
        return "customerLogin";
    }

    @RequestMapping("/customerLogin")
    public String customerLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        boolean isAuthenticated = customerService.authenticateCustomer(username, password);
        if (isAuthenticated) {
            CustomerModel customerModel = customerService.getCustomerByUsername(username);
            model.addAttribute("customerModel", customerModel);
            setCustomerModelWhenLogin(customerModel);
            return "redirect:/customer/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "customerLogin";
        }
    }
    @RequestMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("customerModel",customerModel);
        List<RestaurantModel> restaurantModelList=customerService.getRestautantModelsList();
        model.addAttribute("restaurantModelList",restaurantModelList);
        List<CustomerOrderModel> customerOrderModelList=customerService.getCustomerOrderModelList(customerModel.getCustomerId());
        //HashMap<CustomerOrderModel,LocationModel> orderDetailsMap = customerService.getCustomerOrderDetails(customerModel.getCustomerId());
        //model.addAttribute("orderDetailsMap",orderDetailsMap);
        model.addAttribute("customerOrderModelList",customerOrderModelList);
        return "customerDashboard";
    }

    @RequestMapping("/restaurantRecepies")
    public String getRestaurantRecepies(@RequestParam("restaurantId") int restaurantId,Model model) {
        List<RecepieModel> recepieModelList = customerService.getRecepiesForRestaurant(restaurantId);
        RestaurantModel restaurantModel=customerService.getRestaurantModelById(restaurantId);
        model.addAttribute("customerModel", customerModel);
        model.addAttribute("restaurantModel",restaurantModel );
        model.addAttribute("recepieModelList", recepieModelList);

        return "customerRestaurantRecepies";
    }
    @RequestMapping("/placeOrder")
    public String placeOrder(@RequestParam("recipeId") int recipeId,@RequestParam("locationId") int locationId,Model model) {
        double afterDiscountPrice=customerService.getPriceAfterDiscount(customerModel.getCustomerId(),recipeId);
        RecepieModel recepieModel=customerService.getRecepieModel(recipeId);
        LocationModel locationModel=customerService.getlocationModelById(locationId);
        model.addAttribute("customerModel",customerModel);
        model.addAttribute("recepieModel",recepieModel);
        model.addAttribute("locationModel",locationModel);
        model.addAttribute("afterDiscountPrice",afterDiscountPrice);
        return "customerPlaceOrderDetails";
    }
    @RequestMapping("/confirmOrder")
    public String confirmOrder(@RequestParam("recipeId") int recipeId,@RequestParam("locationId") int locationId,@RequestParam("afterDiscountPrice") double afterDiscountPrice, Model model){
        customerService.confirmOrder(customerModel.getCustomerId(),locationId,recipeId,afterDiscountPrice);
        return "redirect:/customer/dashboard";
    }
    @GetMapping("/searchByRecepieName")
    public String searchByRecipeName(@RequestParam("recipeName") String recipeName,Model model) {
        List<RecepieModel> recepieModelList=customerService.getRecepiesListBySerachRecepieName(recipeName);
        List<LocationModel> locationModelList=customerService.getAllLocationModels();
        model.addAttribute("recepieModelList",recepieModelList);
        model.addAttribute("customerModel",customerModel);
        model.addAttribute("locationModelList",locationModelList);
        return "customerSearchRecepieResult";
    }
    @RequestMapping("searchByPrice")
    public String searchByPrice(@RequestParam("minPrice") double minPrice,@RequestParam("maxPrice") double maxPrice,
            Model model){
        List<RecepieModel> recepieModelList=customerService.getRecepiesByPrice(minPrice,maxPrice);
        List<LocationModel> locationModelList=customerService.getAllLocationModels();
        model.addAttribute("recepieModelList",recepieModelList);
        model.addAttribute("customerModel",customerModel);
        model.addAttribute("locationModelList",locationModelList);
        return "customerSearchRecepieResult";
    }
}
