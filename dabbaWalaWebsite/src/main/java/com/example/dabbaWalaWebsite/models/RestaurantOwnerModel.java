package com.example.dabbaWalaWebsite.models;

import com.example.dabbaWalaWebsite.entity.Customer;
import com.example.dabbaWalaWebsite.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwnerModel {
    private int ownerId;
    private String username;
    private String password;
    private String ownerName;
    private String email;
    private String role="ROLE_RESTAURANTOWNER";
    private List<Restaurant> restaurantList=new ArrayList<>();
    private List<Customer> customerList=new ArrayList<>();

    @Override
    public String toString() {
        return "RestaurantOwnerModel{" +
                "ownerId=" + ownerId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
