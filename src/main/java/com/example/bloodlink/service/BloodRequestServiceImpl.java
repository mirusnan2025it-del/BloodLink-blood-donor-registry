package com.example.bloodlink.service;

import com.example.bloodlink.entity.BloodRequest;
import com.example.bloodlink.entity.Donor;
import com.example.bloodlink.repository.BloodRequestRepository;
import com.example.bloodlink.strategy.BloodCompatibilityStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BloodRequestServiceImpl
        implements BloodRequestService {

    private final BloodRequestRepository repository;
    private final DonorService donorService;
    private final BloodCompatibilityStrategy compatibilityStrategy;

    public BloodRequestServiceImpl(
            BloodRequestRepository repository,
            DonorService donorService,
            BloodCompatibilityStrategy compatibilityStrategy) {

        this.repository = repository;
        this.donorService = donorService;
        this.compatibilityStrategy = compatibilityStrategy;
    }

    @Override
    public BloodRequest createRequest(BloodRequest request) {
        return repository.save(request);
    }

    @Override
    public List<BloodRequest> getAllRequests() {
        return repository.findAll();
    }

    @Override
    public BloodRequest getRequestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BloodRequest updateRequest(
            Long id,
            BloodRequest request) {

        BloodRequest existing =
                repository.findById(id).orElse(null);

        if (existing != null) {

            existing.setRequesterName(
                    request.getRequesterName());

            existing.setBloodType(
                    request.getBloodType());

            existing.setCity(
                    request.getCity());

            existing.setUnitsRequired(
                    request.getUnitsRequired());

            existing.setUrgency(
                    request.getUrgency());

            existing.setPhone(
                    request.getPhone());

            return repository.save(existing);
        }

        return null;
    }

    @Override
    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<BloodRequest> searchByBloodType(
            String bloodType) {

        return repository.findByBloodType(bloodType);
    }

    @Override
    public List<BloodRequest> searchByBloodTypeAndCity(
            String bloodType,
            String city) {

        return repository.findByBloodTypeAndCity(
                bloodType,
                city
        );
    }

    @Override
    public List<Donor> findMatchingDonors(Long requestId) {

        BloodRequest request =
                repository.findById(requestId).orElse(null);

        if (request == null) {
            return List.of();
        }

        // Get only donors who are eligible to donate
        return donorService.getEligibleDonors()
                .stream()

                // Check blood compatibility
                .filter(donor ->
                        compatibilityStrategy.isCompatible(
                                donor.getBloodType(),
                                request.getBloodType()
                        ))

                // Check city
                .filter(donor ->
                        donor.getCity()
                                .equalsIgnoreCase(request.getCity()))

                .collect(Collectors.toList());
    }
}