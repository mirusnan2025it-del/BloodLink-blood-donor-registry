package com.example.bloodlink.service;

import com.example.bloodlink.entity.BloodGroup;

import java.util.List;

public interface BloodGroupService {

    BloodGroup createBloodGroup(BloodGroup bloodGroup);

    List<BloodGroup> getAllBloodGroups();

    BloodGroup getBloodGroupById(Long id);

    BloodGroup updateBloodGroup(Long id, BloodGroup bloodGroup);

    void deleteBloodGroup(Long id);
}