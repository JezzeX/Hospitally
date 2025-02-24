package com.group2.hospitally.model.request.appointment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAppointmentRequest {
    private int patientId;
    private int staffId;
    private String appointmentDate;
    private String appointmentTime;
}
