package com.group2.hospitally.model.request.Patient;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePatientRequest {
    private int hospitalId;
    private String patientName;
    private LocalDate patientDob;
    private String patientGender;
    private String patientContact;
    private String patientAddress;
    private String patientMedicalHistory;
}
