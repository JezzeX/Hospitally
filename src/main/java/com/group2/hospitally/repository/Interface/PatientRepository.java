package com.group2.hospitally.repository.Interface;

import com.group2.hospitally.model.entity.Patient;

import java.util.List;

public interface PatientRepository {
    // get
    Patient getPatientById(int patientId);

    //get all
    List<Patient> getAllPatients();

    //Create
    Patient createPatient(Patient patient);

    //update
    Patient updatePatient(Patient patient);

    //delete
    int deletePatientById(int patientId);
}
