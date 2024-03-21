package com.example.dabbaWalaWebsite.service;

import com.example.dabbaWalaWebsite.conversions.ModelEntityConversions;
import com.example.dabbaWalaWebsite.entity.*;
import com.example.dabbaWalaWebsite.models.*;
import com.example.dabbaWalaWebsite.repository.CustomerRepo;
import com.example.dabbaWalaWebsite.repository.LocationRepo;
import com.example.dabbaWalaWebsite.repository.RestaurantOwnerRepo;
import com.example.dabbaWalaWebsite.repository.RestaurantRepo;
import com.example.dabbaWalaWebsite.serviceImplementations.AdminServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService implements AdminServiceimpl {
    @Autowired
    private AdminModel adminModel;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private RestaurantOwnerRepo restaurantOwnerRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelEntityConversions converter;
    @Override
    public List<LocationModel> getAllLocations() {
        List<Location> locationList=locationRepo.findAll();
        List<LocationModel> locationModels=new ArrayList<>();
        locationList.forEach(location -> locationModels.add(converter.locationEntityToModel(location)));
        return locationModels;
    }
    @Override
    public boolean authenticateAdmin(String username, String password) {
        return adminModel.getUsername().equals(username) && adminModel.getPassword().equals(password);
    }
    @Override
    public void saveLocation(LocationModel locationModel) {
        boolean isAvailable = locationRepo.checkIfAvailable(locationModel.getStreet(), locationModel.getCity(), locationModel.getState());
        if(isAvailable){
            return ;
        }
        Location location= converter.locationModelToEntity(locationModel);
        locationRepo.save(location);
    }
    @Override
    public void deleteLocation(int locationId) {
        Location location=locationRepo.getReferenceById(locationId);
        List<Restaurant> restaurantList=location.getRestaurantList();
        restaurantList.forEach(restaurant -> {
            List<Location> restaurantLocations=restaurant.getLocationList();
            restaurantLocations.remove(location);
            restaurant.setLocationList(restaurantLocations);
            restaurantRepo.save(restaurant);
        });
        locationRepo.delete(locationRepo.getReferenceById(locationId));
    }
    @Override
    public List<RestaurantModel> getAllRestaurants() {
        List<Restaurant> restaurantList= restaurantRepo.findAll();
        List<RestaurantModel> restaurantModelList=new ArrayList<>();
        restaurantList.stream().forEach(restaurant -> restaurantModelList.add(converter.restaurantEntityToModel(restaurant)));
        return restaurantModelList;
    }
    @Override
    public List<RestaurantOwnerModel> getAllRestaurantowners() {
        List<RestaurantOwnerModel> restaurantOwnerModelList=new ArrayList<>();
        List<RestaurantOwner> restaurantOwnerList=restaurantOwnerRepo.findAll();
        restaurantOwnerList.forEach(restaurantOwner -> restaurantOwnerModelList.add(converter.restaurantOwnerEntityToModel(restaurantOwner)));
        return restaurantOwnerModelList;
    }
    @Override
    public List<CustomerModel> getAllCustomersList() {
        List<CustomerModel> customerModelList=new ArrayList<>();
        List<Customer> customerList=customerRepo.findAll();
        customerList.forEach(customer -> customerModelList.add(converter.customerEntityToModel(customer)));
        return customerModelList;
    }
}