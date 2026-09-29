package com.example.bloodlink.repository;

import com.example.bloodlink.entity.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRequestRepository
        extends JpaRepository<BloodRequest, Long> {

    List<BloodRequest> findByBloodType(String bloodType);

    List<BloodRequest> findByBloodTypeAndCity(
            String bloodType,
            String city
    );
}