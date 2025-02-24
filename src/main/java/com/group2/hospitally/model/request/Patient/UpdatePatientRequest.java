package com.group2.hospitally.model.request.Patient;

import lombok.Data;

@Data
public class UpdatePatientRequest {
    private String patientName;
    private String patientDob;
    private String patientGender;
    private String patientContact;
    private String patientAddress;
    private String patientMedicalHistory;
}
