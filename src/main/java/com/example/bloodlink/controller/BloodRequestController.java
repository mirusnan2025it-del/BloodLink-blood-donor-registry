package com.example.bloodlink.controller;

import com.example.bloodlink.entity.BloodRequest;
import com.example.bloodlink.entity.Donor;
import com.example.bloodlink.service.BloodRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-requests")
public class BloodRequestController {

    private final BloodRequestService service;

    public BloodRequestController(BloodRequestService service) {
        this.service = service;
    }

    @PostMapping
    public BloodRequest createRequest(
            @RequestBody BloodRequest request) {

        return service.createRequest(request);
    }

    @GetMapping
    public List<BloodRequest> getAllRequests() {
        return service.getAllRequests();
    }

    @GetMapping("/search")
    public List<BloodRequest> searchRequests(
            @RequestParam String bloodType) {

        return service.searchByBloodType(bloodType);
    }

    @GetMapping("/search/city")
    public List<BloodRequest> searchByBloodTypeAndCity(
            @RequestParam String bloodType,
            @RequestParam String city) {

        return service.searchByBloodTypeAndCity(
                bloodType,
                city
        );
    }

    @GetMapping("/{id}/matching-donors")
    public List<Donor> findMatchingDonors(
            @PathVariable Long id) {

        return service.findMatchingDonors(id);
    }

    @GetMapping("/{id}")
    public BloodRequest getRequestById(
            @PathVariable Long id) {

        return service.getRequestById(id);
    }

    @PutMapping("/{id}")
    public BloodRequest updateRequest(
            @PathVariable Long id,
            @RequestBody BloodRequest request) {

        return service.updateRequest(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteRequest(
            @PathVariable Long id) {

        service.deleteRequest(id);

        return "Blood request deleted successfully";
    }
}