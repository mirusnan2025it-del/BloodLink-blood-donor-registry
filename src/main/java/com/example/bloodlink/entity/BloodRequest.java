package com.example.bloodlink.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blood_requests")
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String requesterName;

    @Column(nullable = false)
    private String bloodType;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private int unitsRequired;

    @Column(nullable = false)
    private String urgency;

    @Column(nullable = false)
    private String phone;

    public BloodRequest() {
    }

    public BloodRequest(String requesterName,
                        String bloodType,
                        String city,
                        int unitsRequired,
                        String urgency,
                        String phone) {

        this.requesterName = requesterName;
        this.bloodType = bloodType;
        this.city = city;
        this.unitsRequired = unitsRequired;
        this.urgency = urgency;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getUnitsRequired() {
        return unitsRequired;
    }

    public void setUnitsRequired(int unitsRequired) {
        this.unitsRequired = unitsRequired;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}