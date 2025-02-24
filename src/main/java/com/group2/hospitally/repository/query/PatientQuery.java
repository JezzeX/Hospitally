package com.group2.hospitally.repository.query;

public class PatientQuery {
    public static final String INSERT_PATIENT = """
            INSERT INTO Patient(patientName, patientDob, patientGender, patientContact, patientAddress,  patientStatus, patientCreatedAt, patientUpdatedAt)
            VALUES (:patientName, :patientDob, :patientGender, :patientContact, :patientAddress, :patientStatus, NOW(), NOW())
            """;

    public static final String GET_ALL_PATIENTS = "SELECT * FROM Patient";

    public static final String GET_PATIENT_BY_ID = "SELECT * FROM Patient WHERE patientId = :patientId";

    public static final String UPDATE_PATIENT_BY_ID = """
            UPDATE Patient
            SET patientName = :patientName,
                patientDob = :patientDob,
                patientGender = :patientGender,
                patientContact = :patientContact,
                patientAddress = :patientAddress,
                patientMedicalHistory = :patientMedicalHistory,
                patientCreatedAt = NOW(),
                patientUpdatedAt = NOW()
            WHERE patientId = :patientId
            """;

    public static final String DELETE_PATIENT_BY_ID = "DELETE FROM Patient WHERE patientId = :patientId";
}
