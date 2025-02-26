package com.group2.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    private int equipmentId;
    private int hospitalId;
    private String equipmentName;
    private String equipmentType;
    private String equipmentStatus;
    private String assignedDepartment;
    private LocalDateTime equipmentCreatedAt;
    private LocalDateTime equipmentUpdatedAt;
}
