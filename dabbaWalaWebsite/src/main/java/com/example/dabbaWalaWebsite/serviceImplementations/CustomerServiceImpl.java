package com.example.dabbaWalaWebsite.serviceImplementations;

import com.example.dabbaWalaWebsite.entity.Recepie;
import com.example.dabbaWalaWebsite.models.*;
import java.util.List;

public interface CustomerServiceImpl {
    public CustomerModel getCustomerByUsername(String username);
    public boolean addCustomer(CustomerModel customerModel);
    public boolean authenticateCustomer(String username, String password);
    public List<RestaurantModel> getRestautantModelsList();
    public List<RecepieModel> getRecepiesForRestaurant(int restaurantId);
    public RestaurantModel getRestaurantModelById(int restaurantId);
    public double getPriceAfterDiscount(int customerId, int recipeId);
    public RecepieModel getRecepieModel(int recipeId);
    public LocationModel getlocationModelById(int locationId);
    public void confirmOrder(int customerId, int locationId, int recipeId, double price);
    public List<CustomerOrderModel> getCustomerOrderModelList(int customerId);
    public List<RecepieModel> getRecepiesListBySerachRecepieName(String recipeName);
    public List<LocationModel> getAllLocationModels();
    public List<Recepie> getAllReceipiesInRangeOfPrice(double minPrice, double maxPrice);
    public List<RecepieModel> getRecepiesByPrice(double minPrice, double maxPrice);
}
