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
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private double amount;

    private int restaurantOwnerId;
    private String restaurantOwnerName;

    private int recepieId;
    private String recepieName;

    private int restaurantId;
    private String restaurantName;

    private int locationId;
    private String locationName;
    private String ownerApproved="PENDING";

    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                ", amount=" + amount +
                ", restaurantOwnerId=" + restaurantOwnerId +
                ", restaurantOwnerName='" + restaurantOwnerName + '\'' +
                ", recepieId=" + recepieId +
                ", recepieName='" + recepieName + '\'' +
                ", restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", ownerApproved='" + ownerApproved + '\'' +
                '}';
    }
}
