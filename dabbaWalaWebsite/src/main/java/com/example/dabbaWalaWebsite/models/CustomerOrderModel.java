package com.example.dabbaWalaWebsite.models;

import com.example.dabbaWalaWebsite.entity.Customer;
import com.example.dabbaWalaWebsite.entity.Location;
import com.example.dabbaWalaWebsite.entity.Restaurant;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderModel {
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

    private Customer customer;

    @Override
    public String toString() {
        return "CustomerOrderModel{" +
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
