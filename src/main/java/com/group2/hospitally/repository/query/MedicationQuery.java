package com.group2.hospitally.repository.query;

public class MedicationQuery {
    public static final String INSERT_MEDICATION = """
            INSERT INTO Medication(hospitalId, medicationName, medicationType, stockQuantity, medicationPrice, medicationStatus, medicationCreatedAt, medicationUpdatedAt)
            VALUES (:hospitalId, :medicationName, :medicationType, :stockQuantity, :medicationPrice, COALESCE(:medicationStatus, 'Active'), GETDATE(), GETDATE())
            """;

    public static final String GET_ALL_MEDICATIONS = "SELECT * FROM Medication";

    public static final String GET_MEDICATION_BY_ID = "SELECT * FROM Medication WHERE medicationId = :medicationId";

    public static final String UPDATE_MEDICATION_BY_ID = """
            UPDATE Medication
            SET hospitalId = :hospitalId,
                medicationName = :medicationName,
                medicationType = :medicationType,
                stockQuantity = :stockQuantity,
                medicationPrice = :medicationPrice,
                medicationStatus = :medicationStatus,
                medicationUpdatedAt = GETDATE()
            WHERE medicationId = :medicationId
            """;

    public static final String DELETE_MEDICATION_BY_ID = "DELETE FROM Medication WHERE medicationId = :medicationId";
}
