package com.example.dabbaWalaWebsite.models;

import com.example.dabbaWalaWebsite.entity.Location;
import com.example.dabbaWalaWebsite.entity.Recepie;
import com.example.dabbaWalaWebsite.entity.RestaurantOwner;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RestaurantModel {
    private int restaurantId;
    private String restaurantName;
    private String street;
    private String city;
    private String state;
    private RestaurantOwner restaurantOwner;
    private List<Recepie> recepieList=new ArrayList<>();
    private List<Location> locationList=new ArrayList<>();

    @Override
    public String toString() {
        return "RestaurantModel{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
