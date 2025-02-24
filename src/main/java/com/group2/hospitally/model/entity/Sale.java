package com.group2.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sale {
    private int saleId;
    private int medicationId;
    private Integer patientId; // Nullable
    private int saleQuantity;
    private double saleTotalPrice;
    private String saleDate;
    private String saleCreatedAt;
}
