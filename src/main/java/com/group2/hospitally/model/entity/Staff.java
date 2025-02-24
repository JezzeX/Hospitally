package com.group2.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Staff {
    private int staffId;
    private int hospitalId;
    private String staffName;
    private String staffRole;
    private String staffContact;
    private String staffDepartment;
    private String staffCreatedAt;
}
