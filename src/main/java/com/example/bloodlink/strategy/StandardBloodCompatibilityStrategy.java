package com.example.bloodlink.strategy;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class StandardBloodCompatibilityStrategy
        implements BloodCompatibilityStrategy {

    private final Map<String, Set<String>> compatibilityMap = Map.of(
            "O-", Set.of("O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"),
            "O+", Set.of("O+", "A+", "B+", "AB+"),
            "A-", Set.of("A-", "A+", "AB-", "AB+"),
            "A+", Set.of("A+", "AB+"),
            "B-", Set.of("B-", "B+", "AB-", "AB+"),
            "B+", Set.of("B+", "AB+"),
            "AB-", Set.of("AB-", "AB+"),
            "AB+", Set.of("AB+")
    );

    @Override
    public boolean isCompatible(
            String donorBloodType,
            String receiverBloodType) {

        if (donorBloodType == null || receiverBloodType == null) {
            return false;
        }

        Set<String> compatibleReceivers =
                compatibilityMap.get(donorBloodType.toUpperCase());

        if (compatibleReceivers == null) {
            return false;
        }

        return compatibleReceivers.contains(
                receiverBloodType.toUpperCase()
        );
    }
}