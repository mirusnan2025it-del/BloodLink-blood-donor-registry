package com.example.bloodlink.service;

import com.example.bloodlink.entity.Donor;
import com.example.bloodlink.exception.ResourceNotFoundException;
import com.example.bloodlink.repository.DonorRepository;
import com.example.bloodlink.strategy.BloodCompatibilityStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonorServiceImpl implements DonorService {

    private final DonorRepository repository;
    private final BloodCompatibilityStrategy compatibilityStrategy;

    public DonorServiceImpl(
            DonorRepository repository,
            BloodCompatibilityStrategy compatibilityStrategy) {

        this.repository = repository;
        this.compatibilityStrategy = compatibilityStrategy;
    }

    @Override
    public Donor createDonor(Donor donor) {
        return repository.save(donor);
    }

    @Override
    public List<Donor> getAllDonors() {
        return repository.findAll();
    }

    @Override
    public Donor getDonorById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donor not found with id: " + id
                        ));
    }

    @Override
    public Donor updateDonor(Long id, Donor donor) {

        Donor existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donor not found with id: " + id
                        ));

        existing.setFullName(donor.getFullName());
        existing.setBloodType(donor.getBloodType());
        existing.setCity(donor.getCity());
        existing.setPhone(donor.getPhone());
        existing.setAvailable(donor.isAvailable());

        return repository.save(existing);
    }

    @Override
    public void deleteDonor(Long id) {

        Donor existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donor not found with id: " + id
                        ));

        repository.delete(existing);
    }

    @Override
    public List<Donor> searchByBloodType(String bloodType) {
        return repository.findByBloodType(bloodType);
    }

    @Override
    public List<Donor> searchByBloodTypeAndCity(
            String bloodType,
            String city) {

        return repository.findByBloodTypeAndCity(
                bloodType,
                city
        );
    }

    @Override
    public List<Donor> findCompatibleDonors(
            String receiverBloodType,
            String city) {

        return repository.findAll()
                .stream()

                // Check 90-day eligibility
                .filter(this::isEligibleToDonate)

                // Check blood compatibility
                .filter(donor ->
                        compatibilityStrategy.isCompatible(
                                donor.getBloodType(),
                                receiverBloodType
                        ))

                // Check city
                .filter(donor ->
                        city == null ||
                                city.isBlank() ||
                                donor.getCity().equalsIgnoreCase(city)
                )

                .collect(Collectors.toList());
    }

    @Override
    public void donateBlood(Long donorId) {

        Donor donor = repository.findById(donorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donor not found with id: " + donorId
                        ));

        donor.setLastDonationDate(LocalDate.now());

        donor.setAvailable(false);

        repository.save(donor);
    }

    @Override
    public LocalDate getNextEligibleDate(Long donorId) {

        Donor donor = repository.findById(donorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donor not found with id: " + donorId
                        ));

        if (donor.getLastDonationDate() == null) {
            return null;
        }

        return donor.getLastDonationDate().plusDays(90);
    }

    @Override
    public List<Donor> getEligibleDonors() {

        return repository.findAll()
                .stream()
                .filter(this::isEligibleToDonate)
                .collect(Collectors.toList());
    }

    private boolean isEligibleToDonate(Donor donor) {

        // Donor has never donated
        if (donor.getLastDonationDate() == null) {
            return donor.isAvailable();
        }

        // Calculate next eligible donation date
        LocalDate nextEligibleDate =
                donor.getLastDonationDate().plusDays(90);

        // Donor becomes eligible automatically after 90 days
        return !LocalDate.now().isBefore(nextEligibleDate);
    }
}