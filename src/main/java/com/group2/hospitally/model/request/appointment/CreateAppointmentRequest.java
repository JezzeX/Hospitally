package com.group2.hospitally.model.request.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAppointmentRequest {
    @NotNull
    private int patientId;

    @NotNull
    private int staffId;

    @NotBlank
    private String appointmentDate;

    @NotBlank
    private String appointmentTime;
}
