package com.example.dabbaWalaWebsite.repository;

import com.example.dabbaWalaWebsite.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Customer findByUsername(String username);
    boolean existsByUsername(String username);
}
