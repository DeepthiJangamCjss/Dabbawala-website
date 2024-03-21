package com.example.dabbaWalaWebsite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ownerId;
    private String username;
    private String password;
    private String ownerName;
    private String email;
    private String role="ROLE_RESTAURANTOWNER";
    @OneToMany(mappedBy = "restaurantOwner")
    @JsonIgnore
    private List<Restaurant> restaurantList=new ArrayList<>();

    @ManyToMany(mappedBy = "restaurantOwnerList")
    private List<Customer> customerList=new ArrayList<>();

    @Override
    public String toString() {
        return "RestaurantOwner{" +
                "ownerId=" + ownerId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
