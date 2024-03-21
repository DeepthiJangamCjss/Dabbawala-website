package com.example.dabbaWalaWebsite.repository;

import com.example.dabbaWalaWebsite.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder,Integer> {
}
