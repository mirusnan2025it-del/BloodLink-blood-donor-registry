package com.example.bloodlink.service;

import com.example.bloodlink.entity.BloodRequest;
import com.example.bloodlink.entity.Donor;

import java.util.List;

public interface BloodRequestService {

    BloodRequest createRequest(BloodRequest request);

    List<BloodRequest> getAllRequests();

    BloodRequest getRequestById(Long id);

    BloodRequest updateRequest(Long id, BloodRequest request);

    void deleteRequest(Long id);

    List<BloodRequest> searchByBloodType(String bloodType);

    List<BloodRequest> searchByBloodTypeAndCity(
            String bloodType,
            String city
    );

    List<Donor> findMatchingDonors(Long requestId);
}