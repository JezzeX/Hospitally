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
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private int patientId;
    private int hospitalId;
    private String patientName;
    private LocalDate patientDob;
    private String patientGender;
    private String patientContact;
    private String patientAddress;
    private String patientMedicalHistory;
    private String patientStatus;
    private LocalDateTime patientCreatedAt;
    private LocalDateTime patientUpdatedAt;
}
