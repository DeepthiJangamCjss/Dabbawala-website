package com.example.dabbaWalaWebsite.service;

import com.example.dabbaWalaWebsite.conversions.ModelEntityConversions;
import com.example.dabbaWalaWebsite.entity.*;
import com.example.dabbaWalaWebsite.models.*;
import com.example.dabbaWalaWebsite.repository.*;
import com.example.dabbaWalaWebsite.serviceImplementations.RestaurantOwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantOwnerService implements RestaurantOwnerServiceImpl {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerOrderRepo customerOrderRepo;
    @Autowired
    private RestaurantOwnerRepo restaurantOwnerRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private RecepieRepo recepieRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private ModelEntityConversions converter;

    public boolean addRestaurantOwner(RestaurantOwnerModel restaurantOwnerModel) {
        boolean ownerExists = restaurantOwnerRepo.existsByUsername(restaurantOwnerModel.getUsername());
        if(ownerExists){
            return false;
            //return "Owner with this username already exists. Try with other username";
        }
        RestaurantOwner restaurantOwner=converter.restaurantOwnerModelToEntity(restaurantOwnerModel);
        String password=passwordEncoder.encode(restaurantOwner.getPassword());
        restaurantOwner.setPassword(password);

        restaurantOwnerRepo.save(restaurantOwner);
        return true;
    }


    public RestaurantOwnerModel getRestaurantOwnerByUsername(String username) {
        RestaurantOwner restaurantOwner=restaurantOwnerRepo.findByUsername(username);
        RestaurantOwnerModel restaurantOwnerModel=converter.restaurantOwnerEntityToModel(restaurantOwner);
        return restaurantOwnerModel;
    }

    public boolean authenticateRestauratOwner(String username, String password) {
        RestaurantOwner restaurantOwner=restaurantOwnerRepo.findByUsername(username);
        return restaurantOwner!=null && passwordEncoder.matches(password,restaurantOwner.getPassword());
    }

    public boolean locationAvailaleInRepo(String street,String city,String state){
        return locationRepo.findAll().stream().anyMatch(location -> location.getStreet().equalsIgnoreCase(street)
            && location.getCity().equalsIgnoreCase(city) && location.getState().equalsIgnoreCase(state)
        );
    }
    public Location findLocationAvailaleInRepo(String street,String city,String state){
        return locationRepo.findAll().stream().filter(location -> location.getStreet().equalsIgnoreCase(street)
                && location.getCity().equalsIgnoreCase(city) && location.getState().equalsIgnoreCase(state)
        ).findFirst().orElse(null);
    }

    public void saveRestaurant(RestaurantOwnerModel restaurantOwnerModel, RestaurantModel restaurantModel) {
        //Restaurant availableRestaurant = restaurantRepo.findByRestaurantName(restaurantModel.getRestaurantName());
        Boolean restaurantNamePresent=existsByRestaurantName(restaurantModel.getRestaurantName());
        //if the restaurant is already present check for location.
        //If location is different save the restaurant
        Location location=new Location();
        location.setStreet(restaurantModel.getStreet());
        location.setCity(restaurantModel.getCity());
        location.setState(restaurantModel.getState());

        RestaurantOwner owner= converter.restaurantOwnerModelToEntity(restaurantOwnerModel);
        List<Restaurant> ownerRestaurantList= owner.getRestaurantList();
        if(restaurantNamePresent){
            List<Restaurant> restaurantList=restaurantRepo.findAll();
            //checking if the restaurant present at that location
            boolean restaurantAvailable = restaurantList.stream()
                    .anyMatch(restaurant ->
                            restaurant.getRestaurantName().equals(restaurantModel.getRestaurantName()) &&
                                    restaurant.getCity().equals(restaurantModel.getCity()) &&
                                    restaurant.getStreet().equals(restaurantModel.getStreet()) &&
                                    restaurant.getState().equals(restaurantModel.getState())
                    );
            //if present do not save here
            if(restaurantAvailable){
                return;
            }
            if(!locationAvailaleInRepo(restaurantModel.getStreet(),restaurantModel.getCity(),restaurantModel.getState())){
                locationRepo.save(location);
                location=findLocationAvailaleInRepo(restaurantModel.getStreet(),restaurantModel.getCity(),restaurantModel.getState());
            }
            Restaurant restaurant=converter.restaurantModelToEntity(restaurantModel);
            ownerRestaurantList.add(restaurant);
            restaurant.setRestaurantOwner(owner);
            owner.setRestaurantList(ownerRestaurantList);
            restaurantRepo.save(restaurant);
            restaurantOwnerRepo.save(owner);

            addLocationToRestaurant(restaurant.getRestaurantId(),location.getLocationId());
            return;
        }
        //If restaurant Name is not there then save the restaurant
        if(!locationAvailaleInRepo(restaurantModel.getStreet(),restaurantModel.getCity(),restaurantModel.getState())){
            locationRepo.save(location);
        }
        location=findLocationAvailaleInRepo(restaurantModel.getStreet(),restaurantModel.getCity(),restaurantModel.getState());
        Restaurant newRestaurant=converter.restaurantModelToEntity(restaurantModel);
        ownerRestaurantList.add(newRestaurant);
        newRestaurant.setRestaurantOwner(owner);
        restaurantRepo.save(newRestaurant);
        restaurantOwnerRepo.save(owner);


        addLocationToRestaurant(newRestaurant.getRestaurantId(),location.getLocationId());
    }

    public Boolean existsByRestaurantName(String restaurantName) {
        List<Restaurant> restaurantList=restaurantRepo.findAll();
        return restaurantList.stream().anyMatch(restaurant -> restaurant.getRestaurantName().equals((restaurantName)));
    }


    public void deleteRestaurant(int restaurantId) {
        boolean restautantIdPresent=restaurantRepo.findById(restaurantId).isPresent();
        if(restautantIdPresent){
            Restaurant restaurant=restaurantRepo.getReferenceById(restaurantId);
            List<Recepie> recepieList=restaurant.getRecepieList();
            recepieList.stream().forEach(recepie ->{
                recepie.setRestaurant(new Restaurant());
                recepieRepo.delete(recepie);
            });
            restaurant.setRecepieList(new ArrayList<>());

            restaurant.setLocationList(new ArrayList<>());
            System.out.println(restaurant);

            restaurant.setRestaurantOwner(new RestaurantOwner());
            restaurantRepo.delete(restaurant);
        }
    }

    public RestaurantModel getRestaurantById(int restaurantId) {
        return converter.restaurantEntityToModel(restaurantRepo.getReferenceById(restaurantId));
    }

    public void meth(){
    }

    public List<Location> getAllLocations() {
        return locationRepo.findAll();
    }

    public void addLocationToRestaurant(int restaurantId, int locationId) {
        Restaurant restaurant=restaurantRepo.getReferenceById(restaurantId);
        Location location=locationRepo.getReferenceById(locationId);

        List<Location> locationList= restaurant.getLocationList();
        locationList.add(location);
        restaurant.setLocationList(locationList);

        List<Restaurant> restaurantList=location.getRestaurantList();
        restaurantList.add(restaurant);
        location.setRestaurantList(restaurantList);

        locationRepo.save(location);
        restaurantRepo.save(restaurant);
    }

    public void removeLocationFromRestaurant(int restaurantId, int locationId) {
        Restaurant restaurant=restaurantRepo.getReferenceById(restaurantId);
        Location location=locationRepo.getReferenceById(locationId);

        List<Location> locationList= restaurant.getLocationList();
        locationList.remove(location);
        restaurant.setLocationList(locationList);

        List<Restaurant> restaurantList=location.getRestaurantList();
        restaurantList.remove(restaurant);
        location.setRestaurantList(restaurantList);

        locationRepo.save(location);
        restaurantRepo.save(restaurant);
    }

    public Restaurant getRestaurantEntityById(int restaurantId) {
        return restaurantRepo.getReferenceById(restaurantId);
    }

    public boolean checkRecipeAvailable(Restaurant restaurant,String recepieName, boolean premiumRecepie) {
        List<Recepie> recepieList=restaurant.getRecepieList();
        String lowercaseRecepieName = recepieName.toLowerCase();
        return recepieList.stream().anyMatch(recepie -> recepie.getRecepieName().toLowerCase().equals(lowercaseRecepieName) && recepie.isPremiumRecepie()==premiumRecepie);
    }
    public void addRecepieToRestaurant(RecepieModel recepieModel) {
        boolean isRecipeAvailable = checkRecipeAvailable(recepieModel.getRestaurant(),recepieModel.getRecepieName(),recepieModel.isPremiumRecepie());
        if(isRecipeAvailable){
            return;
        }
        Recepie recepie=converter.recepieModelToEntity(recepieModel);
        Restaurant restaurant=recepie.getRestaurant();

        List<Recepie> recepieList= restaurant.getRecepieList();
        recepieList.add(recepie);

        restaurantRepo.save(restaurant);
        recepieRepo.save(recepie);
    }


    public int getRestaurantIdFromRecepie(int recepieId) {
        Restaurant restaurant=recepieRepo.getReferenceById(recepieId).getRestaurant();
        return restaurant.getRestaurantId();
    }

    public void deleteRecepieFromRestaurant(int recepieId) {
        Recepie recepie=recepieRepo.getReferenceById(recepieId);
        Restaurant restaurant=recepie.getRestaurant();

        List<Recepie> recepieList= restaurant.getRecepieList();
        recepieList.remove(recepie);

        restaurantRepo.save(restaurant);
        recepieRepo.delete(recepie);
    }

    public List<CustomerOrderModel> getCustomerOrderList(int restaurantId) {
        List<CustomerOrderModel> customerOrderModelList=new ArrayList<>();
        customerOrderRepo.findAll().forEach(customerOrder -> {
            if(customerOrder.getRestaurantId()==restaurantId){
                customerOrderModelList.add(converter.customerOrderEntityToModel(customerOrder));
            }
        });
        return customerOrderModelList;
    }

    public int numberOfSuccessfullDeliveredRecepies(int customerId){
        int numberOfOrders=0;
        Customer customer=customerRepo.getReferenceById(customerId);
        numberOfOrders = (int) customer.getOrderList().stream().filter(customerOrder -> customerOrder.getOwnerApproved().equals("DELIVERED")).count();
        return numberOfOrders;
    }
    public double totalWorthDeliveredToCustomer(int customerId) {
        double totalPrice = 0;
        Customer customer = customerRepo.getReferenceById(customerId);
        for (CustomerOrder customerOrder : customer.getOrderList()) {
            if (customerOrder.getOwnerApproved().equals("DELIVERED")) {
                totalPrice += customerOrder.getAmount();
            }
        }
        return totalPrice;
    }

    public void deliverOrder(int orderId) {
        CustomerOrder customerOrder=customerOrderRepo.getReferenceById(orderId);
        Customer customer=customerOrder.getCustomer();
        double price=customerOrder.getAmount();
        if(customer.getAccountBalance() < price){
            customerOrder.setOwnerApproved("CANCELLED");
            return;
        }
        double balance=customer.getAccountBalance()-price;
        customer.setAccountBalance(balance);
        customerOrder.setOwnerApproved("DELIVERED");
        customerOrderRepo.save(customerOrder);

        //If customer Buys 15 or more orders,can become platinum user
        //If customer buys food worth more than or equals to 2000,can become platinum user
        int numberOfOrders=numberOfSuccessfullDeliveredRecepies(customer.getCustomerId());
        double totalOrdersPrice =totalWorthDeliveredToCustomer(customer.getCustomerId());
        if(numberOfOrders>=15 || totalOrdersPrice >=2000){
            customer.setMembershipType("Platinum");
            customerRepo.save(customer);
        }
    }

    public void cancelOrder(int orderId) {
        CustomerOrder customerOrder=customerOrderRepo.getReferenceById(orderId);
        customerOrder.setOwnerApproved("CANCELLED");
        customerOrderRepo.save(customerOrder);
    }
}
