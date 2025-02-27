package com.group2.hospitally.controller;

import com.group2.hospitally.model.entity.Equipment;
import com.group2.hospitally.model.request.Equipment.CreateEquipmentRequest;
import com.group2.hospitally.model.request.Equipment.UpdateEquipmentRequest;
import com.group2.hospitally.service.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping

    // not needed
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        List<Equipment> equipments = equipmentService.getAllEquipments();
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }

    // get equipments that are specific to a hospital

    @GetMapping("/{equipmentId}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable int equipmentId) {
        Equipment equipment = equipmentService.getEquipmentById(equipmentId);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @GetMapping("/hospital/{hospitalId}/equipment")
    public ResponseEntity<List<Equipment>> getEquipmentByHospitalId(@PathVariable int hospitalId) {
        List<Equipment> equipment = equipmentService.getEquipmentByHospitalId(hospitalId);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @PostMapping("/create-equipment")
    public ResponseEntity<Equipment> createEquipment(@RequestBody @Valid CreateEquipmentRequest request) {
        Equipment equipment = equipmentService.createEquipment(request);
        return new ResponseEntity<>(equipment, HttpStatus.CREATED);
    }

    @PutMapping("/update/{equipmentId}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable int equipmentId, @RequestBody @Valid UpdateEquipmentRequest request) {
        Equipment updatedEquipment = equipmentService.updateEquipment(equipmentId, request);
        return new ResponseEntity<>(updatedEquipment, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{equipmentId}")
    public ResponseEntity<String> deleteEquipment(@PathVariable int equipmentId) {
        equipmentService.deleteEquipmentById(equipmentId);
        return new ResponseEntity<>("Equipment deleted successfully", HttpStatus.OK);
    }
}