package com.example.dabbaWalaWebsite.entity;

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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String username;
    private String password;
    private String name;
    private int age;
    private String email;
    private String membershipType="Normal";
    private double accountBalance;
    private String role="ROLE_CUSTOMER";

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CustomerOrder> orderList=new ArrayList<>();

    @ManyToMany
    @JoinTable(name="restaurantOwner_customer", inverseJoinColumns = @JoinColumn(name="owner_id"),
            joinColumns = @JoinColumn(name="customer_id"))
    private List<RestaurantOwner> restaurantOwnerList=new ArrayList<>();

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", MembershipType='" + membershipType + '\'' +
                ", accountBalance='" + accountBalance + '\'' +
                '}';
    }
}
