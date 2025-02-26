package com.group2.hospitally.model.request.Staff;

import lombok.Data;

@Data
public class CreateStaffRequest {
    private int hospitalId;
    private String staffName;
    private String staffRole;
    private String staffContact;
    private String staffDepartment;
}

