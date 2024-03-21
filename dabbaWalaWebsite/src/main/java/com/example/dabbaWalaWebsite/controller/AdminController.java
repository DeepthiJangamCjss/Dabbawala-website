package com.example.dabbaWalaWebsite.controller;

import com.example.dabbaWalaWebsite.entity.Restaurant;
import com.example.dabbaWalaWebsite.models.CustomerModel;
import com.example.dabbaWalaWebsite.models.LocationModel;
import com.example.dabbaWalaWebsite.models.RestaurantModel;
import com.example.dabbaWalaWebsite.models.RestaurantOwnerModel;
import com.example.dabbaWalaWebsite.service.AdminService;
import com.example.dabbaWalaWebsite.serviceImplementations.AdminServiceimpl;
import com.example.dabbaWalaWebsite.validation.LocationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    private AdminServiceimpl adminService;
    @Autowired
    private LocationValidator locationValidator;
    @RequestMapping("/loginForm")
    public String adminLoginForm(){
        return "adminLogin";
    }
    @RequestMapping("/login")
    public String adminLogin(@RequestParam("username") String username,@RequestParam("password") String password){
        boolean isAuthenticated = adminService.authenticateAdmin(username,password);
        if(!isAuthenticated){
            return "adminLogin";
        }
        return "redirect:dashboard";
    }

    @RequestMapping("/dashboard")
    public String adminDashboard(Model model) {
        return "adminDashboard";
    }

    @RequestMapping("/viewLocations")
    public String viewLocations(Model model) {
        List<LocationModel> locationModelList=adminService.getAllLocations();
        model.addAttribute("locationModelList",locationModelList);
        return "adminViewLocations";
    }

    @RequestMapping("/addLocationForm")
    public String adminAddLocationForm(Model model){
        LocationModel locationModel=new LocationModel();
        model.addAttribute("locationModel",locationModel);
        return "adminAddLocationForm";
    }

    @RequestMapping("/saveLocation")
    public String saveLocation(@ModelAttribute("locationModel") LocationModel locationModel, BindingResult bindingResult){
        locationValidator.validate(locationModel,bindingResult);
        if(bindingResult.hasErrors()){
            return "adminAddLocationForm";
        }
        adminService.saveLocation(locationModel);
        return "redirect:viewLocations";
    }
    @RequestMapping("/deleteLocation")
    public String deleteLocation(@RequestParam("locationId") int locationId) {
        adminService.deleteLocation(locationId);
        return "redirect:/admin/viewLocations";
    }

    @RequestMapping("/viewRestaurants")
    public String viewRestaurants(Model model) {
        List<RestaurantModel> restaurantModelList=adminService.getAllRestaurants();
        model.addAttribute("restaurantModelList",restaurantModelList);
        return "adminViewRestaurants";
    }

    @RequestMapping("/viewRestaurantOwners")
    public String viewRestaurantOwners(Model model)
    {
        List<RestaurantOwnerModel> restaurantOwnerModelList=adminService.getAllRestaurantowners();
        model.addAttribute("restaurantOwnerModelList",restaurantOwnerModelList);
        return "adminViewRestaurantOwners";
    }

    @RequestMapping("/viewCustomers")
    public String viewCustomers(Model model) {
        List<CustomerModel> customerModelList=adminService.getAllCustomersList();
        model.addAttribute("customerModelList",customerModelList);
        return "adminViewCustomers";
    }

}
