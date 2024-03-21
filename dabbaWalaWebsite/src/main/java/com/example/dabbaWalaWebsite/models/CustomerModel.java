package com.example.dabbaWalaWebsite.models;

import com.example.dabbaWalaWebsite.entity.CustomerOrder;
import jakarta.validation.constraints.*;
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
public class CustomerModel {
    private int customerId;
    private String username;
    private String password;
    private String name;
    private int age;
    private String email;
    private String membershipType="Normal";
    private double accountBalance;
    private String role="ROLE_CUSTOMER";
    private List<CustomerOrder> orderList=new ArrayList<>();

    @Override
    public String toString() {
        return "CustomerModel{" +
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
