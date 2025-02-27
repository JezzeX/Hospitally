package com.group2.hospitally.repository.Interface;

import com.group2.hospitally.model.entity.Patient;

import java.util.List;

public interface PatientRepository {
    // get a single patient by id
    Patient getPatientById(int patientId);

    //get all patients on the hms
    List<Patient> getAllPatients();

    // Get all patients in a hospital by hospital ID
    List<Patient> getPatientsByHospitalId(int hospitalId);

    // Create a new patient
    Patient createPatient(Patient patient);

    // Update patient details
    Patient updatePatient(Patient patient);

    // Delete a patient (Change their status to inactive)
    int deletePatientById(int patientId);
}
