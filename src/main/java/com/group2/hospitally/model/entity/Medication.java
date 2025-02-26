package com.group2.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
    private int medicationId;
    private int hospitalId;
    private String medicationName;
    private String medicationType;
    private int stockQuantity;
    private double medicationPrice;
    private String medicationStatus;
    private LocalDateTime medicationCreatedAt;
    private LocalDateTime medicationUpdatedAt;
}
