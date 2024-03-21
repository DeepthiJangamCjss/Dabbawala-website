package com.example.dabbaWalaWebsite.conversions;

import com.example.dabbaWalaWebsite.entity.*;
import com.example.dabbaWalaWebsite.models.*;
import org.springframework.stereotype.Component;

@Component
public class ModelEntityConversions {
    public Restaurant restaurantModelToEntity(RestaurantModel restaurantModel){
        if(restaurantModel==null){
            return null;
        }
        Restaurant restaurant=new Restaurant();
        restaurant.setRestaurantId(restaurantModel.getRestaurantId());
        restaurant.setRestaurantName(restaurantModel.getRestaurantName());
        restaurant.setStreet(restaurantModel.getStreet());
        restaurant.setCity(restaurantModel.getCity());
        restaurant.setState(restaurantModel.getState());
        restaurant.setRestaurantOwner(restaurantModel.getRestaurantOwner());
        restaurant.setRecepieList(restaurantModel.getRecepieList());
        restaurant.setLocationList(restaurantModel.getLocationList());
        return restaurant;
    }
    public RestaurantModel restaurantEntityToModel(Restaurant restaurant) {
        if (restaurant == null) {
            return null;
        }
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setRestaurantId(restaurant.getRestaurantId());
        restaurantModel.setRestaurantName(restaurant.getRestaurantName());
        restaurantModel.setStreet(restaurant.getStreet());
        restaurantModel.setCity(restaurant.getCity());
        restaurantModel.setState(restaurant.getState());
        restaurantModel.setRestaurantOwner(restaurant.getRestaurantOwner());
        restaurantModel.setRecepieList(restaurant.getRecepieList());
        restaurantModel.setLocationList(restaurant.getLocationList());

        return restaurantModel;
    }



    public RestaurantOwner restaurantOwnerModelToEntity(RestaurantOwnerModel restaurantOwnerModel) {
        if(restaurantOwnerModel==null){
            return null;
        }
        RestaurantOwner restaurantOwner=new RestaurantOwner();
        restaurantOwner.setOwnerId(restaurantOwnerModel.getOwnerId());
        restaurantOwner.setUsername(restaurantOwnerModel.getUsername());
        restaurantOwner.setPassword(restaurantOwnerModel.getPassword());
        restaurantOwner.setOwnerName(restaurantOwnerModel.getOwnerName());
        restaurantOwner.setEmail(restaurantOwnerModel.getEmail());
        restaurantOwner.setRestaurantList(restaurantOwnerModel.getRestaurantList());
        return restaurantOwner;
    }
    public RestaurantOwnerModel restaurantOwnerEntityToModel(RestaurantOwner restaurantOwner) {
        if (restaurantOwner == null) {
            return null;
        }
        RestaurantOwnerModel restaurantOwnerModel = new RestaurantOwnerModel();
        restaurantOwnerModel.setOwnerId(restaurantOwner.getOwnerId());
        restaurantOwnerModel.setUsername(restaurantOwner.getUsername());
        restaurantOwnerModel.setPassword(restaurantOwner.getPassword());
        restaurantOwnerModel.setOwnerName(restaurantOwner.getOwnerName());
        restaurantOwnerModel.setEmail(restaurantOwner.getEmail());
        restaurantOwnerModel.setRestaurantList(restaurantOwner.getRestaurantList());
        return restaurantOwnerModel;
    }

    public Recepie recepieModelToEntity(RecepieModel recepieModel){
        if(recepieModel==null){
            return null;
        }
        Recepie recepie=new Recepie();
        recepie.setRecepieId(recepieModel.getRecepieId());
        recepie.setRecepieName(recepieModel.getRecepieName());
        recepie.setPrice(recepieModel.getPrice());
        recepie.setPremiumRecepie(recepieModel.isPremiumRecepie());
        recepie.setRestaurant(recepieModel.getRestaurant());
        return recepie;
    }
    public RecepieModel recepieEntityToModel(Recepie recepie) {
        if(recepie==null){
            return null;
        }
        RecepieModel recepieModel = new RecepieModel();
        recepieModel.setRecepieId(recepie.getRecepieId());
        recepieModel.setRecepieName(recepie.getRecepieName());
        recepieModel.setPrice(recepie.getPrice());
        recepieModel.setPremiumRecepie(recepie.isPremiumRecepie());
        recepieModel.setRestaurant(recepie.getRestaurant());
        return recepieModel;
    }
    public Customer customerModelToEntity(CustomerModel customerModel){
        if(customerModel==null){
            return null;
        }
        Customer customer=new Customer();
        customer.setUsername(customerModel.getUsername());
        customer.setPassword(customerModel.getPassword());
        customer.setCustomerId(customerModel.getCustomerId());
        customer.setName(customerModel.getName());
        customer.setAge(customerModel.getAge());
        customer.setEmail(customerModel.getEmail());
        customer.setAccountBalance(customerModel.getAccountBalance());
        customer.setMembershipType(customerModel.getMembershipType());
        customer.setOrderList(customerModel.getOrderList());
        return customer;
    }
    public CustomerModel customerEntityToModel(Customer customer) {
        if(customer==null){
            return null;
        }
        CustomerModel customerModel = new CustomerModel();
        customerModel.setUsername(customer.getUsername());
        customerModel.setPassword(customer.getPassword());
        customerModel.setCustomerId(customer.getCustomerId());
        customerModel.setName(customer.getName());
        customerModel.setAge(customer.getAge());
        customerModel.setEmail(customer.getEmail());
        customerModel.setAccountBalance(customer.getAccountBalance());
        customerModel.setMembershipType(customer.getMembershipType());
        customerModel.setOrderList(customer.getOrderList());
        return customerModel;
    }
    public Location locationModelToEntity(LocationModel locationModel) {
        if(locationModel==null){
            return null;
        }
        Location location=new Location();
        location.setLocationId(locationModel.getLocationId());
        location.setStreet(locationModel.getStreet());
        location.setCity(locationModel.getCity());
        location.setState(locationModel.getState());
        location.setRestaurantList(locationModel.getRestaurantList());
        return location;
    }

    public LocationModel locationEntityToModel(Location location){
        if(location==null){
            return null;
        }
        LocationModel locationModel=new LocationModel();
        locationModel.setLocationId(location.getLocationId());
        locationModel.setStreet(location.getStreet());
        locationModel.setCity(location.getCity());
        locationModel.setState(location.getState());
        locationModel.setRestaurantList(location.getRestaurantList());
        return locationModel;
    }
    public CustomerOrder customerOrderModelToEntity(CustomerOrderModel customerOrderModel){
        if(customerOrderModel==null){
            return null;
        }
        CustomerOrder customerOrder=new CustomerOrder();
        customerOrder.setOrderId(customerOrderModel.getOrderId());
        customerOrder.setAmount(customerOrderModel.getAmount());
        customerOrder.setOwnerApproved(customerOrderModel.getOwnerApproved());

        customerOrder.setRecepieId(customerOrderModel.getRecepieId());
        customerOrder.setRecepieName(customerOrderModel.getRecepieName());

        customerOrder.setLocationId(customerOrderModel.getLocationId());
        customerOrder.setLocationName(customerOrderModel.getLocationName());

        customerOrder.setCustomer(customerOrderModel.getCustomer());

        customerOrder.setRestaurantOwnerId(customerOrderModel.getRestaurantOwnerId());
        customerOrder.setRestaurantOwnerName(customerOrderModel.getRestaurantOwnerName());

        customerOrder.setRestaurantId(customerOrderModel.getRestaurantId());
        customerOrder.setRestaurantName(customerOrderModel.getRestaurantName());
        return customerOrder;
    }
    public CustomerOrderModel customerOrderEntityToModel(CustomerOrder customerOrder) {
        if(customerOrder == null) {
            return null;
        }
        CustomerOrderModel customerOrderModel = new CustomerOrderModel();
        customerOrderModel.setOrderId(customerOrder.getOrderId());
        customerOrderModel.setAmount(customerOrder.getAmount());
        customerOrderModel.setOwnerApproved(customerOrder.getOwnerApproved());

        customerOrderModel.setRecepieId(customerOrder.getRecepieId());
        customerOrderModel.setRecepieName(customerOrder.getRecepieName());

        customerOrderModel.setLocationId(customerOrder.getLocationId());
        customerOrderModel.setLocationName(customerOrder.getLocationName());

        customerOrderModel.setCustomer(customerOrder.getCustomer());

        customerOrderModel.setRestaurantOwnerId(customerOrder.getRestaurantOwnerId());
        customerOrderModel.setRestaurantOwnerName(customerOrder.getRestaurantOwnerName());

        customerOrderModel.setRestaurantId(customerOrder.getRestaurantId());
        customerOrderModel.setRestaurantName(customerOrder.getRestaurantName());
        return customerOrderModel;
    }
}
