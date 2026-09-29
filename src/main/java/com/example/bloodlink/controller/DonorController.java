package com.example.bloodlink.controller;

import com.example.bloodlink.entity.Donor;
import com.example.bloodlink.service.DonorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorService service;

    public DonorController(DonorService service) {
        this.service = service;
    }

    @PostMapping
    public Donor createDonor(@RequestBody Donor donor) {
        return service.createDonor(donor);
    }

    @GetMapping
    public List<Donor> getAllDonors() {
        return service.getAllDonors();
    }

    @GetMapping("/{id}")
    public Donor getDonorById(@PathVariable Long id) {
        return service.getDonorById(id);
    }

    @PutMapping("/{id}")
    public Donor updateDonor(
            @PathVariable Long id,
            @RequestBody Donor donor) {

        return service.updateDonor(id, donor);
    }

    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {
        service.deleteDonor(id);
        return "Donor deleted successfully";
    }

    @GetMapping("/search")
    public List<Donor> searchDonors(
            @RequestParam String bloodType) {

        return service.searchByBloodType(bloodType);
    }

    @GetMapping("/search/city")
    public List<Donor> searchByBloodTypeAndCity(
            @RequestParam String bloodType,
            @RequestParam String city) {

        return service.searchByBloodTypeAndCity(
                bloodType,
                city
        );
    }

    @GetMapping("/compatible")
    public List<Donor> findCompatibleDonors(
            @RequestParam String receiverBloodType,
            @RequestParam(required = false) String city) {

        return service.findCompatibleDonors(
                receiverBloodType,
                city
        );
    }

    @PostMapping("/{id}/donate")
    public String donateBlood(@PathVariable Long id) {

        service.donateBlood(id);

        return "Blood donation recorded successfully. Donor is unavailable for 90 days.";
    }

    @GetMapping("/{id}/next-eligible-date")
    public String getNextEligibleDate(@PathVariable Long id) {

        var nextEligibleDate = service.getNextEligibleDate(id);

        if (nextEligibleDate == null) {
            return "Donor has not donated yet. Donor is eligible to donate.";
        }

        return "Next eligible donation date: " + nextEligibleDate;
    }
}