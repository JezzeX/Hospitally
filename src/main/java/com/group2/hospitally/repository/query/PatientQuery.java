package com.group2.hospitally.repository.query;

public class PatientQuery {
    // Insert a new patient
    public static final String INSERT_PATIENT = """
    INSERT INTO Patient (hospitalId, patientName, patientDob, patientGender, patientContact, patientAddress,patientMedicalHistory)
    VALUES (:hospitalId, :patientName, :patientDob, :patientGender, :patientContact, :patientAddress,:patientMedicalHistory)""";

    // Retrieve all patients on the HMS
    public static final String GET_ALL_PATIENTS =
      "SELECT * FROM Patient WHERE patientStatus = 'Active'";

    // Retrieve a single patient using their ID
    public static final String GET_PATIENT_BY_ID =
       "SELECT * FROM Patient WHERE patientId = :patientId AND patientStatus = 'Active'";

    // Retrieve patients of a particular hospital by hospital ID
    public static final String GET_PATIENTS_BY_HOSPITAL_ID =
       "SELECT * FROM Patient WHERE patientStatus = 'Active' AND hospitalId = :hospitalId;";

    // Update patient details
    public static final String UPDATE_PATIENT_BY_ID = """
            UPDATE Patient
            SET patientName = :patientName,
                patientDob = :patientDob,
                patientGender = :patientGender,
                patientContact = :patientContact,
                patientAddress = :patientAddress,
                patientMedicalHistory = :patientMedicalHistory,
                patientUpdatedAt = GETDATE()
            WHERE patientId = :patientId AND patientStatus = 'Active'
            """;

    // Delete a patient (Change status to 'Inactive')
    public static final String DELETE_PATIENT_BY_ID = """
            UPDATE Patient
            SET patientStatus = 'Inactive',
                patientUpdatedAt = GETDATE()
            WHERE patientId = :patientId AND patientStatus = 'Active'""";
}
