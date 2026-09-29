package com.example.bloodlink.strategy;

public interface BloodCompatibilityStrategy {

    boolean isCompatible(
            String donorBloodType,
            String receiverBloodType
    );
}