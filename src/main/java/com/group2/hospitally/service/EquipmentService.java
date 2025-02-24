package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Equipment;
import com.group2.hospitally.model.request.Equipment.CreateEquipmentRequest;
import com.group2.hospitally.repository.Interface.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.getAllEquipments();
    }

    public Equipment getEquipmentById(int equipmentId) {
        return equipmentRepository.getEquipmentById(equipmentId);
    }

    public Equipment getEquipmentByHospitalId(int hospitalId) {
        return equipmentRepository.getEquipmentByHospitalId(hospitalId);
    }

    public Equipment createEquipment(CreateEquipmentRequest request) {
        Gson gson = new Gson();
        Equipment equipment = gson.fromJson(gson.toJson(request), Equipment.class);
        return equipmentRepository.createEquipment(equipment);
    }

    public Equipment updateEquipment(int equipmentId, CreateEquipmentRequest request) {
        // Retrieve the equipment to check if it exists
        Equipment existingEquipment = equipmentRepository.getEquipmentById(equipmentId);
        if (existingEquipment == null) {
            throw new RuntimeException("Equipment with ID " + equipmentId + " not found");
        }

        // Update the equipment details
        existingEquipment.setHospitalId(request.getHospitalId());
        existingEquipment.setEquipmentName(request.getEquipmentName());
        existingEquipment.setEquipmentType(request.getEquipmentType());
        existingEquipment.setAssignedDepartment(request.getAssignedDepartment());

        // Save the updated equipment
        return equipmentRepository.updateEquipment(existingEquipment);
    }

    public void deleteEquipmentById(int equipmentId) {
        equipmentRepository.deleteEquipmentById(equipmentId);
    }
}