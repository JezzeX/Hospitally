package com.group2.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Medication {
    private int medicationId;
    private int hospitalId;
    private String medicationName;
    private String medicationType;
    private int stockQuantity;
    private double medicationPrice;
    private String medicationCreatedAt;
}
