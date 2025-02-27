package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Equipment;
import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.request.Equipment.CreateEquipmentRequest;
import com.group2.hospitally.model.request.Equipment.UpdateEquipmentRequest;
import com.group2.hospitally.repository.Interface.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        try
        {
            return equipmentRepository.getAllEquipments();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving equipments",e);
        }

    }

    public Equipment getEquipmentById(int equipmentId) {
        try
        {
            Equipment equipment = equipmentRepository.getEquipmentById(equipmentId);
            if(equipment == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found");
            }
             return equipment;
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving equipment",e);
        }
    }

    public List<Equipment> getEquipmentByHospitalId(int hospitalId) {
        try {
            List<Equipment> equipment = equipmentRepository.getEquipmentByHospitalId(hospitalId);
            if(equipment == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment in hospital with id"+hospitalId+" not found");
            }
            return equipment;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving equipments",e);
        }
    }

    public Equipment createEquipment(CreateEquipmentRequest request) {
        // Check if the hospitalId exists before creating the equipment
        try {
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

            return equipmentRepository.createEquipment(equipment);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating equipment",e);
        }
    }

    public Equipment updateEquipment(int equipmentId, UpdateEquipmentRequest request) {
        // Retrieve the equipment to check if it exists
        try {
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
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating equipment",e);
        }
    }

    public void deleteEquipmentById(int equipmentId) {
        try
        {
            Equipment existingEquipment = equipmentRepository.getEquipmentById(equipmentId);
            if (existingEquipment == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment with ID " + equipmentId + " not found");
            }
            equipmentRepository.deleteEquipmentById(equipmentId);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting equipment",e);
        }

    }
}