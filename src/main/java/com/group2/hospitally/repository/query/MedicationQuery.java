package com.group2.hospitally.repository.query;

public class MedicationQuery {
    public static final String INSERT_MEDICATION = """
            INSERT INTO Medication(medicationHospitalId, medicationName, medicationType, stockQuantity, medicationPrice, medicationStatus, medicationCreatedAt, medicationUpdatedAt)
            VALUES (:medicationHospitalId, :medicationName, :medicationType, :stockQuantity, :medicationPrice, COALESCE(:medicationStatus, 'Active'), GETDATE(), GETDATE())
            """;

    public static final String GET_ALL_MEDICATIONS = "SELECT * FROM Medication WHERE medicationStatus == 'Active'";

    public static final String GET_MEDICATION_BY_ID = "SELECT * FROM Medication WHERE medicationId = :medicationId AND medicationStatus = 'Active'";

    public static final String UPDATE_MEDICATION_BY_ID = """
            UPDATE Medication
            SET medicationHospitalId = :medicationHospitalId,
                medicationName = :medicationName,
                medicationType = :medicationType,
                stockQuantity = :stockQuantity,
                medicationPrice = :medicationPrice,
                medicationStatus = :medicationStatus,
                medicationUpdatedAt = GETDATE()
            WHERE medicationId = :medicationId
            """;

    public static final String DELETE_MEDICATION_BY_ID = """
            UPDATE Medication
            SET medicationStatus = 'Deleted',
                medicationUpdatedAt = GETDATE()
            WHERE medicationId = :medicationId
            """;

    public static final String GET_MEDICATION_BY_HOSPITAL_ID = "SELECT * FROM Medication WHERE medicationHospitalId = :medicationHospitalId AND medicationStatus = 'Active'";

    public static final String GET_MEDICATION_BY_TYPE = "SELECT * FROM Medication WHERE medicationType = :medicationType AND medicationHospitalId = :medicationHospitalId  AND medicationStatus = 'Active'";

    public static final String UPDATE_STOCK_QUANTITY = """
    UPDATE Medication
    SET stockQuantity = :stockQuantity,
        medicationUpdatedAt = GETDATE()
    WHERE medicationId = :medicationId
""";

}