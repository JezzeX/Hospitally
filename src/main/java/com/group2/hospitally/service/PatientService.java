package com.group2.hospitally.service;

import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.entity.Patient;
import com.group2.hospitally.model.request.Patient.CreatePatientRequest;
import com.group2.hospitally.model.request.Patient.UpdatePatientRequest;
import com.group2.hospitally.repository.Interface.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            return patientRepository.getAllPatients();
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Patients", e);
        }
    }

    //Get a single patient by their ID
    public Patient getPatientById(int patientId) {
        try {
            Patient patient = patientRepository.getPatientById(patientId);
            if (patient == null) {
                throw new RuntimeException("Patient with id " + patientId + " not found");
            }
            return patient;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Patient with id " + patientId, e);
        }
    }

    // Get all patients in a hospital by hospital ID
    public List<Patient> getPatientsByHospitalId(int hospitalId) {
        try {
            Hospital hospital = hospitalService.getHospitalById(hospitalId);
            if (hospital == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital with id " + hospitalId + " not found");
            }
            return patientRepository.getPatientsByHospitalId(hospitalId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Patients", e);
        }
    }

    //Create a new Patient
    public Patient createPatient(CreatePatientRequest request) {
        try {
            Hospital hospital = hospitalService.getHospitalById(request.getHospitalId());
            if (hospital == null) {
                throw new RuntimeException("Hospital with ID " + request.getHospitalId() + " does not exist yet.");
            }
            Patient patient = Patient.builder()
                    .hospitalId(request.getHospitalId())
                    .patientName(request.getPatientName())
                    .patientDob(request.getPatientDob())
                    .patientGender(request.getPatientGender())
                    .patientContact(request.getPatientContact())
                    .patientAddress(request.getPatientAddress())
                    .patientMedicalHistory(request.getPatientMedicalHistory())
                    .patientStatus("Active")
                    .patientCreatedAt(LocalDateTime.now())
                    .patientUpdatedAt(LocalDateTime.now())
                    .build();
            return patientRepository.createPatient(patient);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Creating Patient", e);
        }
    }

    //Update an existing Patient
    public Patient updatePatient(int patientId, UpdatePatientRequest request) {
        try {
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
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Updating Patient", e);
        }
    }

    //Delete a patient (Changing their status)
    public void deletePatientById(int patientId) {
        try {
            Patient existingPatient = getPatientById(patientId);

            if (existingPatient == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with ID " + patientId + " not found");
            }

            // Soft delete: Update the patient's status to "Inactive"
            existingPatient.setPatientStatus("Inactive");
            existingPatient.setPatientUpdatedAt(LocalDateTime.now());

            // Call the repository to update the patient's status in the database
            int rowsAffected = patientRepository.deletePatientById(patientId);

            if (rowsAffected == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete patient. No rows were affected.");
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while deleting patient", e);
        }
    }

}
