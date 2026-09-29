package com.example.bloodlink.controller;

import com.example.bloodlink.entity.BloodGroup;
import com.example.bloodlink.service.BloodGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-groups")
public class BloodGroupController {

    private final BloodGroupService service;

    public BloodGroupController(BloodGroupService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public BloodGroup createBloodGroup(@RequestBody BloodGroup bloodGroup) {
        return service.createBloodGroup(bloodGroup);
    }

    // READ ALL
    @GetMapping
    public List<BloodGroup> getAllBloodGroups() {
        return service.getAllBloodGroups();
    }

    // READ ONE
    @GetMapping("/{id}")
    public BloodGroup getBloodGroupById(@PathVariable Long id) {
        return service.getBloodGroupById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public BloodGroup updateBloodGroup(
            @PathVariable Long id,
            @RequestBody BloodGroup bloodGroup) {

        return service.updateBloodGroup(id, bloodGroup);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteBloodGroup(@PathVariable Long id) {

        service.deleteBloodGroup(id);

        return "Blood group deleted successfully";
    }
}