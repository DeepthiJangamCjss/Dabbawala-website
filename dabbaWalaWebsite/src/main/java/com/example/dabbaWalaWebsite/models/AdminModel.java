package com.example.dabbaWalaWebsite.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Setter
@Getter
public class AdminModel {
    private String username;
    private String password;
    public AdminModel(){
        username="admin";
        password="admin";
    }

    @Override
    public String toString() {
        return "AdminModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
