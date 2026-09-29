package com.example.bloodlink.service;

import com.example.bloodlink.entity.BloodGroup;
import com.example.bloodlink.repository.BloodGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodGroupServiceImpl implements BloodGroupService {

    private final BloodGroupRepository repository;

    public BloodGroupServiceImpl(BloodGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public BloodGroup createBloodGroup(BloodGroup bloodGroup) {
        return repository.save(bloodGroup);
    }

    @Override
    public List<BloodGroup> getAllBloodGroups() {
        return repository.findAll();
    }

    @Override
    public BloodGroup getBloodGroupById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BloodGroup updateBloodGroup(Long id, BloodGroup bloodGroup) {

        BloodGroup existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setBloodType(bloodGroup.getBloodType());
            return repository.save(existing);
        }

        return null;
    }

    @Override
    public void deleteBloodGroup(Long id) {
        repository.deleteById(id);
    }
}