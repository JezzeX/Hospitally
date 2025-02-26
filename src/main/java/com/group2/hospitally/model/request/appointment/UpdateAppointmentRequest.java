package com.group2.hospitally.model.request.appointment;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAppointmentRequest {

    @NotBlank
    private String appointmentDate;

    @NotBlank
    private String appointmentTime;
}
