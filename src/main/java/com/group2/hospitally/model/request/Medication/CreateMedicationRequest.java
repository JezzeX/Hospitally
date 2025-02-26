package com.group2.hospitally.model.request.Medication;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMedicationRequest {

    @NotNull
    private int hospitalId;

    @NotBlank
    private String medicationName;

    @NotBlank
    private String medicationType;

    @NotNull
    private int stockQuantity;

    @NotNull
    private double medicationPrice;
}
