package com.example.bloodlink.service;

import com.example.bloodlink.entity.Donor;

import java.time.LocalDate;
import java.util.List;

public interface DonorService {

    Donor createDonor(Donor donor);

    List<Donor> getAllDonors();

    Donor getDonorById(Long id);

    Donor updateDonor(Long id, Donor donor);

    void deleteDonor(Long id);

    List<Donor> searchByBloodType(String bloodType);

    List<Donor> searchByBloodTypeAndCity(
            String bloodType,
            String city
    );

    List<Donor> findCompatibleDonors(
            String receiverBloodType,
            String city
    );

    void donateBlood(Long donorId);

    LocalDate getNextEligibleDate(Long donorId);

    List<Donor> getEligibleDonors();
}