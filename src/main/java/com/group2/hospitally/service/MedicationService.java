package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.entity.Medication;
import com.group2.hospitally.model.request.Medication.CreateMedicationRequest;
import com.group2.hospitally.model.request.Medication.UpdateMedicationRequest;
import com.group2.hospitally.repository.Interface.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final HospitalService hospitalService;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository, HospitalService hospitalService) {
        this.medicationRepository = medicationRepository;
        this.hospitalService = hospitalService;
    }

    public List<Medication> getAllMedications() {
        try {
            return medicationRepository.getAllMedications();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error retrieving medications",e);
        }
    }

    public Medication getMedicationById(int medicationId) {
        try {
            Medication medication = medicationRepository.getMedicationById(medicationId);
            if (medication == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medication not found");
            }
            return medication;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error retrieving medication",e);
        }
    }

    public List<Medication> getMedicationByHospitalId(int hospitalId) {
        try {
            List<Medication> medication = medicationRepository.getMedicationByHospitalId(hospitalId);
            if (medication == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medications in hospital with ID" + hospitalId + " not found");
            }
            return medicationRepository.getMedicationByHospitalId(hospitalId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error retrieving medications",e);
        }
    }

 public List<Medication> getMedicationByType(int hospitalId, String medicationType) {
        try {
            List<Medication> medication = medicationRepository.getMedicationByType(hospitalId, medicationType);
            if (medication == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error retrieving medications with type " + medicationType);
            }
            return medication;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error retrieving medications",e);
        }
    }

    public Medication createMedication(CreateMedicationRequest request) {
        try {
            Hospital hospital = hospitalService.getHospitalById(request.getHospitalId());
            if (hospital == null) {
                throw new RuntimeException("Hospital with ID " + request.getHospitalId() + " does not exist.");
            }

            Medication medication = new Medication();

            medication.setHospitalId(request.getHospitalId());
            medication.setMedicationName(request.getMedicationName());
            medication.setMedicationType(request.getMedicationType());
            medication.setMedicationStatus("Active");
            medication.setStockQuantity(request.getStockQuantity());
            medication.setMedicationCreatedAt(LocalDateTime.now());
            medication.setMedicationUpdatedAt(LocalDateTime.now());

            return medicationRepository.createMedication(medication);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error creating medication", e);
        }
}



    public Medication updateMedication(int medicationId, UpdateMedicationRequest request) {
        try {
            // Retrieve the medication to check if it exists
            Medication existingMedication = medicationRepository.getMedicationById(medicationId);
            if (existingMedication == null) {
                throw new RuntimeException("Medication with ID " + medicationId + " not found");
            }

            // Update the medication details
            existingMedication.setMedicationName(request.getMedicationName());
            existingMedication.setMedicationType(request.getMedicationType());
            existingMedication.setStockQuantity(request.getStockQuantity());
            existingMedication.setMedicationPrice(request.getMedicationPrice());

            // Save the updated medication
            return medicationRepository.updateMedication(existingMedication);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error updating medication",e);
        }
    }

    public void deleteMedicationById(int medicationId) {
        try{
            Medication existingMedication = medicationRepository.getMedicationById(medicationId);
            if (existingMedication == null) {
                throw new RuntimeException("Medication with ID " + medicationId + " not found");
            }
            medicationRepository.deleteMedicationById(medicationId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error deleting medication",e);
        }
    }
}