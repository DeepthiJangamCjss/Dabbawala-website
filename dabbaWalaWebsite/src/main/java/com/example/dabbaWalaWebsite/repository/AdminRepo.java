package com.example.dabbaWalaWebsite.repository;

import com.example.dabbaWalaWebsite.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);
}
