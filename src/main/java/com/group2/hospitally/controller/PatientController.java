package com.group2.hospitally.controller;

import com.group2.hospitally.model.entity.Patient;
import com.group2.hospitally.model.request.Patient.CreatePatientRequest;
import com.group2.hospitally.model.request.Patient.UpdatePatientRequest;
import com.group2.hospitally.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Get all patients on the HMS
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // get all the patients that belong to a hospital
    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<Patient>> getPatientsByHospitalId(@PathVariable int hospitalId) {
        List<Patient> patients = patientService.getPatientsByHospitalId(hospitalId);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Get a single patient by their ID
    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientId) {
        Patient patient = patientService.getPatientById(patientId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
    // Get all patients based on status (Active/Inactive)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Patient>> getPatientsByStatus(@PathVariable String status) {
        List<Patient> patients = patientService.getPatientsByStatus(status);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    // Create a new patient
    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@RequestBody CreatePatientRequest request) {
        Patient patient = patientService.createPatient(request);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    // Update an existing patient
    @PutMapping("/update/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int patientId, @RequestBody UpdatePatientRequest request) {
        Patient updatedPatient = patientService.updatePatient(patientId, request);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<String> deletePatientById(@PathVariable int patientId) {
        patientService.deletePatientById(patientId);
        return new ResponseEntity<>("Patient marked as inactive", HttpStatus.OK);
    }
}
