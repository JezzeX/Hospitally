package com.group2.hospitally.controller;

import com.group2.hospitally.model.entity.Medication;
import com.group2.hospitally.model.request.Medication.CreateMedicationRequest;
import com.group2.hospitally.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public ResponseEntity<List<Medication>> getAllMedications() {
        List<Medication> medications = medicationService.getAllMedications();
        return new ResponseEntity<>(medications, HttpStatus.OK);
    }

    @GetMapping("/{medicationId}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable int medicationId) {
        Medication medication = medicationService.getMedicationById(medicationId);
        return new ResponseEntity<>(medication, HttpStatus.OK);
    }

    @GetMapping("/hospital/{hospitalId}/medication")
    public ResponseEntity<Medication> getMedicationByHospitalId(@PathVariable int hospitalId) {
        Medication medication = medicationService.getMedicationByHospitalId(hospitalId);
        return new ResponseEntity<>(medication, HttpStatus.OK);
    }

//    get medication by type
    @GetMapping("/hospital/{hospitalId}/medication/{medicationType}")
    public ResponseEntity<Medication> getMedicationByHospitalIdAndMedicationType(@PathVariable int hospitalId, @PathVariable String medicationType) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Medication> createMedication(@RequestBody @Valid CreateMedicationRequest request) {
        Medication medication = medicationService.createMedication(request);
        return new ResponseEntity<>(medication, HttpStatus.CREATED);
    }

    @PutMapping("/update/{medicationId}")
    public ResponseEntity<Medication> updateMedication(@PathVariable int medicationId, @RequestBody @Valid CreateMedicationRequest request) {
        Medication updatedMedication = medicationService.updateMedication(medicationId, request);
        return new ResponseEntity<>(updatedMedication, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{medicationId}")
    public ResponseEntity<String> deleteMedication(@PathVariable int medicationId) {
        medicationService.deleteMedicationById(medicationId);
        return new ResponseEntity<>("Medication deleted successfully", HttpStatus.OK);
    }
}