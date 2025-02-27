package com.group2.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    private int saleId;
    private int medicationId;
    private int patientId; // Nullable
    private int saleQuantity;
    private double saleTotalPrice;
    private LocalDate saleDate;
    private LocalDateTime saleCreatedAt;
}
