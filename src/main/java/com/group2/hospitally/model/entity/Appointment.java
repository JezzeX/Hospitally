package com.group2.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class Appointment {
    private int appointmentId;
    private int patientId;
    private int staffId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String appointmentStatus;
    private LocalDateTime appointmentCreatedAt;
    private LocalDateTime appointmentUpdatedAt;
}
