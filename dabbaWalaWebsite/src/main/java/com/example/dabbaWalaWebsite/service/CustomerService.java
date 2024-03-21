package com.example.dabbaWalaWebsite.service;

import com.example.dabbaWalaWebsite.conversions.ModelEntityConversions;
import com.example.dabbaWalaWebsite.entity.*;
import com.example.dabbaWalaWebsite.models.*;
import com.example.dabbaWalaWebsite.repository.*;
import com.example.dabbaWalaWebsite.serviceImplementations.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements CustomerServiceImpl {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private RecepieRepo recepieRepo;
    @Autowired
    private ModelEntityConversions converter;
    @Autowired
    private CustomerOrderRepo customerOrderRepo;

    public CustomerModel getCustomerByUsername(String username) {
        Customer customer=customerRepo.findByUsername(username);
        CustomerModel customerModel=converter.customerEntityToModel(customer);
        return customerModel;
    }

    public boolean addCustomer(CustomerModel customerModel) {
        Customer customerFound = customerRepo.findByUsername(customerModel.getUsername());
        if(customerFound!=null){
            //return "User with this username Already Exists. Try with other username";
            return false;
        }
        Customer customer=converter.customerModelToEntity(customerModel);
        String encodedPassword=passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customerRepo.save(customer);
        return true;
        //return "Customer added Successfully";
    }

    public boolean authenticateCustomer(String username, String password) {
        Customer customer=customerRepo.findByUsername(username);
        return customer!=null && passwordEncoder.matches(password,customer.getPassword());
        //return customer!=null && customer.getPassword().equals(password);
    }

    public List<RestaurantModel> getRestautantModelsList() {
        List<RestaurantModel> restaurantModelList=new ArrayList<>();
        List<Restaurant> restaurantList=restaurantRepo.findAll();
        restaurantList.forEach(restaurant -> restaurantModelList.add(converter.restaurantEntityToModel(restaurant)));
        System.out.println(restaurantModelList);
        return restaurantModelList;
    }

    public List<RecepieModel> getRecepiesForRestaurant(int restaurantId) {
        List<RecepieModel> recepieModelList=new ArrayList<>();
        Restaurant restaurant=restaurantRepo.getReferenceById(restaurantId);
        List<Recepie> recepieList=restaurant.getRecepieList();
        recepieList.forEach(recepie -> recepieModelList.add(converter.recepieEntityToModel(recepie)));
        return recepieModelList;
    }

    public RestaurantModel getRestaurantModelById(int restaurantId) {
        Restaurant restaurant=restaurantRepo.getReferenceById(restaurantId);
        return converter.restaurantEntityToModel(restaurant);
    }

    public double getPriceAfterDiscount(int customerId, int recipeId) {
        Customer customer=customerRepo.getReferenceById(customerId);
        Recepie recepie=recepieRepo.getReferenceById(recipeId);
        if(customer.getMembershipType().equals("Normal")) {
            //Normal users has 3% discount
            return  (double) recepie.getPrice() -(double) recepie.getPrice()*3/100;
        } else {
            //Platinum users has 5% discount
            return (double) recepie.getPrice() -(double) recepie.getPrice()*5/100;
        }
    }

    public RecepieModel getRecepieModel(int recipeId) {
        Recepie recepie=recepieRepo.getReferenceById(recipeId);
        return converter.recepieEntityToModel(recepie);
    }

    public LocationModel getlocationModelById(int locationId) {
        Location location=locationRepo.getReferenceById(locationId);
        return converter.locationEntityToModel(location);
    }

    private boolean recepiefoundAtThatLocation(int locationId, int recipeId) {
        Recepie recepie=recepieRepo.getReferenceById(recipeId);
        Restaurant restaurant=recepie.getRestaurant();
        List<Location> locationList=restaurant.getLocationList();
        return locationList.stream().anyMatch(location -> location.getLocationId()==locationId);
    }

    public void confirmOrder(int customerId, int locationId, int recipeId, double price) {
        boolean isAvailable= recepiefoundAtThatLocation(locationId,recipeId);
        if(!isAvailable){
            return;
        }

        CustomerOrder customerOrder=new CustomerOrder();

        Recepie recepie=recepieRepo.getReferenceById(recipeId);
        customerOrder.setRecepieId(recipeId);
        customerOrder.setRecepieName(recepie.getRecepieName());


        customerOrder.setAmount(price);

        customerOrder.setRestaurantId(recepie.getRestaurant().getRestaurantId());
        customerOrder.setRestaurantName(recepie.getRestaurant().getRestaurantName());

        customerOrder.setRestaurantOwnerId(recepie.getRestaurant().getRestaurantOwner().getOwnerId());
        customerOrder.setRestaurantOwnerName(recepie.getRestaurant().getRestaurantOwner().getOwnerName());

        Location location=locationRepo.getReferenceById(locationId);
        customerOrder.setLocationId(locationId);
        customerOrder.setLocationName(location.getStreet()+", "+location.getCity()+", "+location.getState());

        Customer customer=customerRepo.getReferenceById(customerId);
        customerOrder.setCustomer(customer);


        customerOrderRepo.save(customerOrder);
    }

    public List<CustomerOrderModel> getCustomerOrderModelList(int customerId) {
        Customer customer=customerRepo.getReferenceById(customerId);
        List<CustomerOrderModel> customerOrderModelList=new ArrayList<>();
        customer.getOrderList().forEach(customerOrder -> customerOrderModelList.add(converter.customerOrderEntityToModel(customerOrder)));
        return customerOrderModelList;
    }

    public List<RecepieModel> getRecepiesListBySerachRecepieName(String recipeName) {
        String recepieNameLower=recipeName.toLowerCase();
        List<RecepieModel> recepieModelList=new ArrayList<>();
        List<Recepie> recepieList=recepieRepo.findAll().stream().filter(recepie -> recepie.getRecepieName().toLowerCase().contains(recepieNameLower)).toList();
        recepieList.forEach(recepie -> recepieModelList.add(converter.recepieEntityToModel(recepie)));
        return recepieModelList;
    }

    public List<LocationModel> getAllLocationModels() {
        List<Location> locationList=locationRepo.findAll();
        List<LocationModel> locationModelList=new ArrayList<>();
        locationList.forEach(location -> locationModelList.add(converter.locationEntityToModel(location)));
        return locationModelList;
    }

    public List<Recepie> getAllReceipiesInRangeOfPrice(double minPrice, double maxPrice) {
        return recepieRepo.findAll().stream().filter(recepie -> recepie.getPrice() >= minPrice && recepie.getPrice() <= maxPrice).collect(Collectors.toList());
    }
    public List<RecepieModel> getRecepiesByPrice(double minPrice, double maxPrice) {
        List<RecepieModel> recepieModelList=new ArrayList<>();
        List<Recepie> recepieList=getAllReceipiesInRangeOfPrice(minPrice,maxPrice);
        recepieList.forEach(recepie -> recepieModelList.add(converter.recepieEntityToModel(recepie)));
        return recepieModelList;
    }
}
