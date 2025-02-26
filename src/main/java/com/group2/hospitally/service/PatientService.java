package com.group2.hospitally.service;

import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.entity.Patient;
import com.group2.hospitally.model.request.Patient.CreatePatientRequest;
import com.group2.hospitally.model.request.Patient.UpdatePatientRequest;
import com.group2.hospitally.repository.Interface.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final HospitalService hospitalService;

    @Autowired
    public PatientService(PatientRepository patientRepository, HospitalService hospitalService) {
        this.patientRepository = patientRepository;
        this.hospitalService = hospitalService;
    }
    //Get all patients on the HMS
    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }
    //Get a single patient by their ID
    public Patient getPatientById(int patientId) {
        return patientRepository.getPatientById(patientId);
    }
    // Get all patients in a hospital by hospital ID
    public List<Patient> getPatientsByHospitalId(int hospitalId) {
        return patientRepository.getPatientsByHospitalId(hospitalId);
    }
    // Get all current or deleted patients by status (Active/Inactive)
    public List<Patient> getPatientsByStatus(String status) {
        return patientRepository.getPatientsByStatus(status);
    }
    //Create a new Patient
    public Patient createPatient(CreatePatientRequest request) {
        Hospital hospital = hospitalService.getHospitalById(request.getHospitalId());
            if (hospital == null) {
                throw new RuntimeException("Hospital with ID " + request.getHospitalId() + " does not exist.");
            }
        Patient patient = new Patient();
        patient.setHospitalId(request.getHospitalId());
        patient.setPatientName(request.getPatientName());
        patient.setPatientDob(request.getPatientDob());
        patient.setPatientGender(request.getPatientGender());
        patient.setPatientContact(request.getPatientContact());
        patient.setPatientAddress(request.getPatientAddress());
        patient.setPatientMedicalHistory(request.getPatientMedicalHistory());
        patient.setPatientStatus("Active");
        patient.setPatientCreatedAt(LocalDateTime.now());
        patient.setPatientUpdatedAt(LocalDateTime.now());

        return patientRepository.createPatient(patient);
    }

    //Update an existing Patient
    public Patient updatePatient(int patientId, UpdatePatientRequest request) {
        // Retrieve the patient to check if it exists
        Patient existingPatient = patientRepository.getPatientById(patientId);
        if (existingPatient == null) {
            throw new RuntimeException("Patient with ID " + patientId + " not found");
        }

        // Update the patient details if they exist
        existingPatient.setPatientName(request.getPatientName());
        existingPatient.setPatientAddress(request.getPatientAddress());
        existingPatient.setPatientDob(request.getPatientDob());
        existingPatient.setPatientGender(request.getPatientGender());
        existingPatient.setPatientContact(request.getPatientContact());
        existingPatient.setPatientMedicalHistory(request.getPatientMedicalHistory());
        existingPatient.setPatientUpdatedAt(LocalDateTime.now());

        // Save the updated patient
        return patientRepository.updatePatient(existingPatient);
    }
    //Delete a patient (Changing their status)
    public void deletePatientById(int patientId) {
      Patient existingPatient = getPatientById(patientId);

      // If the patient does not exist, throw an exception
       if (existingPatient == null) {
            throw new RuntimeException("Patient with ID " + patientId + " not found");
      }
      // Update the patient's status to "Inactive" to indicate a soft delete
      existingPatient.setPatientStatus("Inactive");
      existingPatient.setPatientUpdatedAt(LocalDateTime.now());

      // Call the repository to update the patient's status in the database
      int rowsAffected = patientRepository.deletePatientById(patientId);

      // Check if the update was successful; if not, throw an exception
        if (rowsAffected == 0) {
           throw new RuntimeException("Failed to delete patient. No rows were affected.");
        }
    }
}
