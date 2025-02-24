package com.group2.hospitally.model.request.Equipment;

import lombok.Data;

@Data
public class CreateEquipmentRequest {
    private int hospitalId;
    private String equipmentName;
    private String equipmentType;
    private String assignedDepartment;
}
