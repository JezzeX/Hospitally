package com.group2.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Patient {
    private int patientId;
    private int hospitalId;
    private String patientName;
    private String patientDob;
    private String patientGender;
    private String patientContact;
    private String patientAddress;
    private String patientMedicalHistory;
    private String patientCreatedAt;
}
