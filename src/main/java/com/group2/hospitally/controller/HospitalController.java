package com.group2.hospitally.controller;

import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.request.Hospital.CreateHospitalRequest;
import com.group2.hospitally.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable int hospitalId) {
        Hospital hospital = hospitalService.getHospitalById(hospitalId);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Hospital> createHospital(@RequestBody CreateHospitalRequest request) {
        Hospital hospital = hospitalService.createHospital(request);
        return new ResponseEntity<>(hospital, HttpStatus.CREATED);
    }

    @PutMapping("/update/{hospitalId}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable int hospitalId, @RequestBody CreateHospitalRequest request) {
        Hospital updatedHospital = hospitalService.updateHospital(hospitalId, request);
        return new ResponseEntity<>(updatedHospital, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{hospitalId}")
    public ResponseEntity<String> deleteHospital(@PathVariable int hospitalId) {
        hospitalService.deleteHospitalById(hospitalId);
        return new ResponseEntity<>("Hospital deleted successfully", HttpStatus.OK);
    }
}
