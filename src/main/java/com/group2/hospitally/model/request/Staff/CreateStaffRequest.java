package com.group2.hospitally.model.request.Staff;

import lombok.Data;

@Data
public class CreateStaffRequest {
    private String staffName;
    private String staffRole;
    private String staffContact;
    private String staffDepartment;
    private String staffCreatedAt;
    private String staffUpdatedAt;
    private String staffStatus;
}

