package com.example.dabbaWalaWebsite.serviceImplementations;

import com.example.dabbaWalaWebsite.entity.*;
import com.example.dabbaWalaWebsite.models.CustomerOrderModel;
import com.example.dabbaWalaWebsite.models.RecepieModel;
import com.example.dabbaWalaWebsite.models.RestaurantModel;
import com.example.dabbaWalaWebsite.models.RestaurantOwnerModel;

import java.util.List;

public interface RestaurantOwnerServiceImpl {
    public boolean addRestaurantOwner(RestaurantOwnerModel restaurantOwnerModel);
    public RestaurantOwnerModel getRestaurantOwnerByUsername(String username);
    public boolean authenticateRestauratOwner(String username, String password);
    public boolean locationAvailaleInRepo(String street,String city,String state);
    public Location findLocationAvailaleInRepo(String street,String city,String state);
    public void saveRestaurant(RestaurantOwnerModel restaurantOwnerModel, RestaurantModel restaurantModel);
    public Boolean existsByRestaurantName(String restaurantName);
    public void deleteRestaurant(int restaurantId) ;
    public RestaurantModel getRestaurantById(int restaurantId);
    public List<Location> getAllLocations();
    public void addLocationToRestaurant(int restaurantId, int locationId) ;
    public void removeLocationFromRestaurant(int restaurantId, int locationId) ;
    public Restaurant getRestaurantEntityById(int restaurantId) ;
    public boolean checkRecipeAvailable(Restaurant restaurant,String recepieName, boolean premiumRecepie) ;
    public void addRecepieToRestaurant(RecepieModel recepieModel) ;
    public int getRestaurantIdFromRecepie(int recepieId);
    public void deleteRecepieFromRestaurant(int recepieId);
    public List<CustomerOrderModel> getCustomerOrderList(int restaurantId);
    public int numberOfSuccessfullDeliveredRecepies(int customerId);
    public double totalWorthDeliveredToCustomer(int customerId);
    public void deliverOrder(int orderId) ;
    public void cancelOrder(int orderId);
}
