package com.example.dabbaWalaWebsite.serviceImplementations;
import com.example.dabbaWalaWebsite.models.*;
import java.util.List;

public interface AdminServiceimpl {
    public List<LocationModel> getAllLocations();
    public boolean authenticateAdmin(String username, String password);
    public void saveLocation(LocationModel locationModel);
    public void deleteLocation(int locationId);
    public List<RestaurantModel> getAllRestaurants();
    public List<RestaurantOwnerModel> getAllRestaurantowners();
    public List<CustomerModel> getAllCustomersList();
}
