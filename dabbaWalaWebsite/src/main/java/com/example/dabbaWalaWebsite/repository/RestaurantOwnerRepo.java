package com.example.dabbaWalaWebsite.repository;

import com.example.dabbaWalaWebsite.entity.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantOwnerRepo extends JpaRepository<RestaurantOwner,Integer> {
    boolean existsByUsername(String username);
    RestaurantOwner findByUsername(String username);
}
