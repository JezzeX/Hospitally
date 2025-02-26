package com.group2.hospitally.model.request.Patient;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePatientRequest {

    @NotBlank
    private String patientName;

    @NotBlank
    private String patientDob;

    @NotBlank
    private String patientGender;

    @NotBlank
    private String patientContact;

    @NotBlank
    private String patientAddress;

    @NotBlank
    private String patientMedicalHistory;
}
