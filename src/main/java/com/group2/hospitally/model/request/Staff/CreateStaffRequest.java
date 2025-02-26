package com.group2.hospitally.model.request.Staff;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateStaffRequest {

    @NotNull
    private int hospitalId;

    @NotBlank
    private String staffName;

    @NotBlank
    private String staffRole;

    @NotBlank
    private String staffContact;

    @NotBlank
    private String staffDepartment;
}

