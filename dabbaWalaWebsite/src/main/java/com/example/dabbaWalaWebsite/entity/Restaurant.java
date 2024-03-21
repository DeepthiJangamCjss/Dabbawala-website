package com.example.dabbaWalaWebsite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restaurantId;
    private String restaurantName;
    private String street;
    private String city;
    private String state;

    @ManyToOne
    @JoinColumn(name="ownerId")
    @JsonIgnore
    private RestaurantOwner restaurantOwner;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Recepie> recepieList=new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="restaurant_location", joinColumns = @JoinColumn(name="restaurantId"),
    inverseJoinColumns = @JoinColumn(name="locationId"))
    @JsonIgnore
    private List<Location> locationList=new ArrayList<>();

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
