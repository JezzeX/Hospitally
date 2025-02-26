package com.group2.hospitally.model.request.Hospital;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateHospitalRequest {

    @NotBlank
    private String hospitalName;

    @NotBlank
    private String hospitalAddress;

    @NotBlank
    private String hospitalPhoneNo;

    @NotBlank
    private String hospitalEmail;
}
