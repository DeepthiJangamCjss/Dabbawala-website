package com.example.dabbaWalaWebsite.models;

import com.example.dabbaWalaWebsite.entity.Restaurant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecepieModel {
    private int recepieId;
    private String recepieName;
    private int price;
    private boolean isPremiumRecepie;
    private Restaurant restaurant;

    public RecepieModel(String recepieName, int price, boolean isPremiumRecepie) {
        this.recepieName = recepieName;
        this.price = price;
        this.isPremiumRecepie = isPremiumRecepie;
    }

    @Override
    public String toString() {
        return "RecepieModel{" +
                "recepieId=" + recepieId +
                ", recepieName='" + recepieName + '\'' +
                ", price=" + price +
                ", isPremiumRecepie=" + isPremiumRecepie +
                ", restaurant=" + restaurant +
                '}';
    }
}
