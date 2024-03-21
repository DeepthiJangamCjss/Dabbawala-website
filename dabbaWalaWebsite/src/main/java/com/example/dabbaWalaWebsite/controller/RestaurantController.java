package com.example.dabbaWalaWebsite.controller;

import com.example.dabbaWalaWebsite.entity.Location;
import com.example.dabbaWalaWebsite.entity.Restaurant;
import com.example.dabbaWalaWebsite.models.CustomerOrderModel;
import com.example.dabbaWalaWebsite.models.RecepieModel;
import com.example.dabbaWalaWebsite.models.RestaurantModel;
import com.example.dabbaWalaWebsite.models.RestaurantOwnerModel;
import com.example.dabbaWalaWebsite.serviceImplementations.RestaurantOwnerServiceImpl;
import com.example.dabbaWalaWebsite.validation.RecepieValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant")
@Controller
public class RestaurantController {
    @Autowired
    private RestaurantOwnerServiceImpl restaurantOwnerService;
    @Autowired
    private RecepieValidator recepieValidator;
    private RestaurantOwnerModel restaurantOwnerModel;
    public void setRestaurantOwnerModelWhenLogin(RestaurantOwnerModel restaurantOwnerModel){
        this.restaurantOwnerModel=restaurantOwnerModel;
    }
    @RequestMapping("/restaurantOwnerLoginForm")
    public String restaurantOwnerLoginForm(){
        return "restaurantOwnerLogin";
    }
    @RequestMapping("/restaurantOwnerLogin")
    public String restaurantOwnerLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
        boolean isAuthenticated = restaurantOwnerService.authenticateRestauratOwner(username, password);
        System.out.println(isAuthenticated);
        if (isAuthenticated) {
            RestaurantOwnerModel restaurantOwnerModel= restaurantOwnerService.getRestaurantOwnerByUsername(username);
            model.addAttribute("restaurantOwnerModel",restaurantOwnerModel);
            setRestaurantOwnerModelWhenLogin(restaurantOwnerModel);
            return "restaurantOwnerDashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "redirect:restaurantOwnerLoginForm";
        }
    }
    @RequestMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("restaurantOwnerModel",restaurantOwnerModel);
        return "restaurantOwnerDashboard";
    }
    @RequestMapping("/addRestaurantForm")
    public String addRestaurantForm(Model model){
        RestaurantModel restaurantModel=new RestaurantModel();
        model.addAttribute("restaurantModel",restaurantModel);
        return "addRestaurantForm";
    }
    @RequestMapping("/saveRestaurant")
    public String saveRestaurant(@ModelAttribute("restaurantModel") RestaurantModel restaurantModel,Model model){
        restaurantOwnerService.saveRestaurant(restaurantOwnerModel,restaurantModel);
        model.addAttribute("restaurantOwnerModel",restaurantOwnerModel);
        return "restaurantOwnerDashboard";
    }
    @RequestMapping("/viewRestaurantDetails")
    public String viewRestaurantDetails(@RequestParam("restaurantId") int restaurantId,Model model){
        RestaurantModel restaurantModel=restaurantOwnerService.getRestaurantById(restaurantId);
        model.addAttribute("restaurantModel",restaurantModel);
        List<CustomerOrderModel> customerOrderModelList=restaurantOwnerService.getCustomerOrderList(restaurantId);
        model.addAttribute("customerOrderModelList",customerOrderModelList);
        return "viewRestaurantDetails";
    }
    @RequestMapping("/deleteRestaurant")
    public String deleteRestaurant(@RequestParam("restaurantId") int restaurantId,Model model){
        restaurantOwnerService.deleteRestaurant(restaurantId);
        restaurantOwnerModel=restaurantOwnerService.getRestaurantOwnerByUsername(restaurantOwnerModel.getUsername());
        model.addAttribute("restaurantOwnerModel",restaurantOwnerModel);
        return "restaurantOwnerDashboard";
    }
    @RequestMapping("/addLocation")
    public String addLocationToRestaurant(@RequestParam("restaurantId") int restaurantId,Model model){
        RestaurantModel restaurantModel=restaurantOwnerService.getRestaurantById(restaurantId);
        List<Location> allLocations=restaurantOwnerService.getAllLocations();
        model.addAttribute("allLocations",allLocations);
        model.addAttribute("restaurantModel",restaurantModel);
        return "addLocationToRestaurant";
    }

    @RequestMapping("/addOrRemoveLocation")
    public String addOrRemoveLocation(@RequestParam("restaurantId") int restaurantId,@RequestParam("locationId") int locationId,@RequestParam("action") String action,Model model) {

        if ("add".equals(action)) {
            restaurantOwnerService.addLocationToRestaurant(restaurantId,locationId);
        } else if ("remove".equals(action)) {
            restaurantOwnerService.removeLocationFromRestaurant(restaurantId,locationId);
        }
        RestaurantModel restaurantModel=restaurantOwnerService.getRestaurantById(restaurantId);
        List<Location> allLocations=restaurantOwnerService.getAllLocations();
        model.addAttribute("allLocations",allLocations);
        model.addAttribute("restaurantModel",restaurantModel);
        return "addLocationToRestaurant";
    }
    @RequestMapping("/addRecipe")
    public String addRecipe(@RequestParam("restaurantId") int restaurantId,Model model) {
        Restaurant restaurant=restaurantOwnerService.getRestaurantEntityById(restaurantId);
        RecepieModel recepieModel=new RecepieModel();
        recepieModel.setRestaurant(restaurant);
        model.addAttribute("restaurantId",restaurantId);
        model.addAttribute("recepieModel", recepieModel);
        return "addRecepieToRestautantForm";
    }
    @RequestMapping("/saveRecipe")
    public String saveRecipe(@ModelAttribute("recepieModel") @Valid RecepieModel recepieModel,BindingResult bindingResult,Model model) {
        recepieValidator.validate(recepieModel,bindingResult);
        if (bindingResult.hasErrors()) {
            return "addRecepieToRestaurantForm";
        }
        int restaurantId=recepieModel.getRestaurant().getRestaurantId();
        restaurantOwnerService.addRecepieToRestaurant(recepieModel);
        return "redirect:/restaurant/viewRestaurantDetails?restaurantId=" + restaurantId;
    }
    @RequestMapping("/deleteRecipe")
    public String deleteRecipe(@RequestParam("recepieId") int recepieId) {
        int restaurantId=restaurantOwnerService.getRestaurantIdFromRecepie(recepieId);
        restaurantOwnerService.deleteRecepieFromRestaurant(recepieId);
        return "redirect:/restaurant/viewRestaurantDetails?restaurantId=" + restaurantId;
    }
    @RequestMapping("/deliverOrder")
    public String deliverOrder(@RequestParam("orderId") int orderId, @RequestParam("restaurantId") int restaurantId) {
        restaurantOwnerService.deliverOrder(orderId);
        return "redirect:/restaurant/dashboard?restaurantId=" + restaurantId;
    }

    @RequestMapping("/cancelOrder")
    public String cancelOrder(@RequestParam("orderId") int orderId, @RequestParam("restaurantId") int restaurantId) {
        restaurantOwnerService.cancelOrder(orderId);
        return "redirect:/restaurant/viewRestaurantDetails?restaurantId=" + restaurantId;
    }
}
