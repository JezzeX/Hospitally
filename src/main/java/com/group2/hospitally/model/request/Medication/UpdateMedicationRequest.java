package com.group2.hospitally.model.request.Medication;


import lombok.Data;

@Data
public class UpdateMedicationRequest {
    private String medicationName;
    private String medicationType;
    private int stockQuantity;
    private double medicationPrice;
}
