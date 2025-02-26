package com.group2.hospitally.repository.query;

public class SaleQuery {

    public static final String GET_SALE_BY_ID = """
        SELECT * FROM Sale WHERE saleId = :saleId
    """;

    public static final String GET_ALL_SALES = """
        SELECT * FROM Sale
    """;

    public static final String INSERT_SALE = """
       INSERT INTO Sale (medicationId, patientId, saleQuantity, saleTotalPrice, saleDate, saleCreatedAt, saleUpdatedAt) VALUES (:medicationId, :patientId, :saleQuantity, :saleTotalPrice, GETDATE(), GETDATE(), GETDATE());                                                                                UPDATE Medication
            SET stockQuantity = stockQuantity - :saleQuantity
            WHERE medicationId = :medicationId;
    """;

    public static final String UPDATE_SALE_BY_ID = """
        UPDATE Sale
        SET medicationId = :medicationId, 
            patientId = :patientId, 
            saleQuantity = :saleQuantity, 
            saleTotalPrice = :saleTotalPrice, 
            saleDate = :saleDate, 
            saleUpdatedAt = GETDATE()
        WHERE saleId = :saleId
    """;

    public static final String DELETE_SALE_BY_ID = """
        DELETE FROM Sale WHERE saleId = :saleId
    """;

    public static final String GET_SALE_BY_MEDICATION = "SELECT * FROM SALE WHERE medicationId = :medicationId";

    public static final String GET_SALE_BY_PATIENT = "SELECT * FROM SALE WHERE patientId = :patientId";

    public static final String GET_SALE_BY_HOSPITAL_ID = """
        SELECT H.hospitalName, S.*
        FROM Sale S
        LEFT JOIN Medication M ON M.medicationId = S.medicationId
        LEFT JOIN Hospital H ON M.hospitalId = H.hospitalId
        WHERE H.hospitalId = :hospitalId
    """;
}