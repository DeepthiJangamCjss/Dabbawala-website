package com.example.dabbaWalaWebsite.repository;

import com.example.dabbaWalaWebsite.entity.Recepie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecepieRepo extends JpaRepository<Recepie,Integer> {
}
