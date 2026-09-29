package com.example.bloodlink.repository;

import com.example.bloodlink.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    List<Donor> findByBloodType(String bloodType);

    List<Donor> findByBloodTypeAndCity(
            String bloodType,
            String city
    );

    List<Donor> findByBloodTypeAndAvailable(
            String bloodType,
            boolean available
    );
}