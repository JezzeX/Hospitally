package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Patient;
import com.group2.hospitally.model.request.Patient.CreatePatientRequest;
import com.group2.hospitally.repository.Interface.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }

    public Patient getPatientById(int patientId) {
        return patientRepository.getPatientById(patientId);
    }

    public Patient createPatient(CreatePatientRequest request) {
        Gson gson = new Gson();
        Patient patient = gson.fromJson(gson.toJson(request), Patient.class);
        return patientRepository.createPatient(patient);
    }

    public Patient updatePatient(int patientId, CreatePatientRequest request) {
        // Retrieve the patient to check if it exists
        Patient existingPatient = patientRepository.getPatientById(patientId);
        if (existingPatient == null) {
            throw new RuntimeException("Patient with ID " + patientId + " not found");
        }

        // Update the patient details
        existingPatient.setPatientName(request.getPatientName());
        existingPatient.setPatientAddress(request.getPatientAddress());
        existingPatient.setPatientDob(request.getPatientDob());
        existingPatient.setPatientGender(request.getPatientGender());
        existingPatient.setPatientContact(request.getPatientContact());
        existingPatient.setPatientMedicalHistory(request.getPatientMedicalHistory());

        // Save the updated patient
        return patientRepository.updatePatient(existingPatient);
    }

    public void deletePatientById(int patientId) {
        patientRepository.deletePatientById(patientId);
    }
}
