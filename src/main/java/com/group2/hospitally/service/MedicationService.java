package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.entity.Medication;
import com.group2.hospitally.model.request.Medication.CreateMedicationRequest;
import com.group2.hospitally.repository.Interface.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return medicationRepository.getAllMedications();
    }

    public Medication getMedicationById(int medicationId) {
        return medicationRepository.getMedicationById(medicationId);
    }

    public List<Medication> getMedicationByHospitalId(int hospitalId) {
        return medicationRepository.getMedicationByHospitalId(hospitalId);
    }

 public List<Medication> getMedicationByType(int hospitalId, String medicationType) {
        return medicationRepository.getMedicationByType(hospitalId, medicationType);
    }

    public Medication createMedication(CreateMedicationRequest request) {
        Hospital hospital = hospitalService.getHospitalById(request.getHospitalId());
        if (hospital == null) {
           return null;
        }
        Gson gson = new Gson();
        Medication medication = gson.fromJson(gson.toJson(request), Medication.class);
        return medicationRepository.createMedication(medication);
    }

    public Medication updateMedication(int medicationId, CreateMedicationRequest request) {
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
    }

    public void deleteMedicationById(int medicationId) {
        medicationRepository.deleteMedicationById(medicationId);
    }
}