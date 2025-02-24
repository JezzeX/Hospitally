package com.group2.hospitally.repository.Interface;

import com.group2.hospitally.model.entity.Equipment;
import com.group2.hospitally.model.entity.Medication;

import java.util.List;

public interface EquipmentRepository {
    Equipment getEquipmentById(int equipmentId);

    // Get medication by Hospital Id
    Equipment getEquipmentByHospitalId(int hospitalId);

    // Get all equipment
    List<Equipment> getAllEquipments();

    // Create new equipment
    Equipment createEquipment(Equipment equipment);

    // Update existing equipment
    Equipment updateEquipment(Equipment equipment);

    // Delete equipment by ID
    int deleteEquipmentById(int equipmentId);
}
