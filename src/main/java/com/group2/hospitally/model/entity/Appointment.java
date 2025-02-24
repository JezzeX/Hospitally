package com.group2.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Appointment {
    private int appointmentId;
    private int patientId;
    private int staffId;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentStatus;
    private String appointmentCreatedAt;
    private String appointmentUpdatedAt;
}
