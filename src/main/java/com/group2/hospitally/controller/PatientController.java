package com.group2.hospitally.controller;

import com.group2.hospitally.model.entity.Patient;
import com.group2.hospitally.model.request.Patient.CreatePatientRequest;
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

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientId) {
        Patient patient = patientService.getPatientById(patientId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@RequestBody CreatePatientRequest request) {
        Patient patient = patientService.createPatient(request);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping("/update/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int patientId, @RequestBody CreatePatientRequest request) {
        Patient updatedPatient = patientService.updatePatient(patientId, request);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable int patientId) {
        patientService.deletePatientById(patientId);
        return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
    }
}
