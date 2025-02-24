package com.group2.hospitally.model.request.Hospital;

import lombok.Data;

@Data
public class UpdateHospitalRequest {
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalPhoneNo;
    private String hospitalEmail;
}
