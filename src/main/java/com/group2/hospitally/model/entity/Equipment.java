package com.group2.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Equipment {
    private int equipmentId;
    private int hospitalId;
    private String equipmentName;
    private String equipmentType;
    private String equipmentStatus;
    private String assignedDepartment;
    private String equipmentCreatedAt;
    private String equipmentUpdatedAt;
}
