package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Equipment;
import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.request.Equipment.CreateEquipmentRequest;
import com.group2.hospitally.model.request.Equipment.UpdateEquipmentRequest;
import com.group2.hospitally.repository.Interface.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final HospitalService hospitalService;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository, HospitalService hospitalService) {
        this.equipmentRepository = equipmentRepository;
        this.hospitalService = hospitalService;
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.getAllEquipments();
    }

    public Equipment getEquipmentById(int equipmentId) {
        return equipmentRepository.getEquipmentById(equipmentId);
    }

    public List<Equipment> getEquipmentByHospitalId(int hospitalId) {
        return equipmentRepository.getEquipmentByHospitalId(hospitalId);
    }

    public Equipment createEquipment(CreateEquipmentRequest request) {
        // Check if the hospitalId exists before creating the equipment
        Hospital hospital = hospitalService.getHospitalById(request.getHospitalId());
        if (hospital == null) {
            throw new RuntimeException("Hospital with ID " + request.getHospitalId() + " does not exist.");
        }
        Equipment equipment = new Equipment();
        
        equipment.setHospitalId(request.getHospitalId());
        equipment.setEquipmentName(request.getEquipmentName());
        equipment.setEquipmentType(request.getEquipmentType());
        equipment.setAssignedDepartment(request.getAssignedDepartment());
        equipment.setEquipmentStatus("Active");
        equipment.setEquipmentCreatedAt(LocalDateTime.now());
        equipment.setEquipmentUpdatedAt(LocalDateTime.now());

        return  equipmentRepository.createEquipment(equipment);
    }

    public Equipment updateEquipment(int equipmentId, UpdateEquipmentRequest request) {
        // Retrieve the equipment to check if it exists
        Equipment existingEquipment = equipmentRepository.getEquipmentById(equipmentId);
        if (existingEquipment == null) {
            throw new RuntimeException("Equipment with ID " + equipmentId + " not found");
        }

        // Update the equipment details
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