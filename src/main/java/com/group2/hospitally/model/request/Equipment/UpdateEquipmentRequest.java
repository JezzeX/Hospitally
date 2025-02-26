package com.group2.hospitally.model.request.Equipment;

import lombok.Data;

@Data
public class UpdateEquipmentRequest {
    private String equipmentName;
    private String equipmentType;
    private String assignedDepartment;
}
