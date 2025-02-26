package com.group2.hospitally.model.request.Staff;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateStaffRequest {

    @NotBlank
    private String staffName;

    @NotBlank
    private String staffRole;

    @NotBlank
    private String staffContact;

    @NotBlank
    private String staffDepartment;
 }
