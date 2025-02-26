package com.group2.hospitally.model.request.Medication;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateMedicationRequest {

    @NotBlank
    private int hospitalId;

    @NotBlank
    private String medicationName;

    @NotBlank
    private String medicationType;

    @NotBlank
    private int stockQuantity;

    @NotBlank
    private double medicationPrice;
}
