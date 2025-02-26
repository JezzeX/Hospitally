package com.group2.hospitally.model.request.Equipment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEquipmentRequest {
    private int hospitalId;
    private String equipmentName;
    private String equipmentType;
    private String assignedDepartment;
}
