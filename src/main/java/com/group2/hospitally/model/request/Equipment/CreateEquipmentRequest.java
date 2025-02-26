package com.group2.hospitally.model.request.Equipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateEquipmentRequest {

    @NotNull
    private int hospitalId;

    @NotBlank
    private String equipmentName;

    @NotBlank
    private String equipmentType;

    @NotBlank
    private String assignedDepartment;
}
