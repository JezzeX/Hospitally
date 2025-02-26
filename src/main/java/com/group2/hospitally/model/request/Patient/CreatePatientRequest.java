package com.group2.hospitally.model.request.Patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePatientRequest {

    @NotNull
    private int hospitalId;

    @NotBlank
    private String patientName;

    @NotNull
    private LocalDate patientDob;

    @NotBlank
    private String patientGender;

    @NotBlank
    private String patientContact;

    @NotBlank
    private String patientAddress;

    @NotBlank
    private String patientMedicalHistory;
}
