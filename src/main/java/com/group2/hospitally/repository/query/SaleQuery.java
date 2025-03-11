package com.group2.hospitally.repository.query;

public class SaleQuery {

    public static final String GET_SALE_BY_ID = """
        SELECT * FROM Sale WHERE saleId = :saleId
    """;

    public static final String GET_ALL_SALES = """
        SELECT * FROM Sale
    """;

    public static final String INSERT_SALE = """
       INSERT INTO Sale (saleMedicationId, salePatientId, saleQuantity, saleTotalPrice, saleDate, saleCreatedAt) VALUES (:saleMedicationId, :salePatientId, :saleQuantity, :saleTotalPrice, GETDATE(), GETDATE());
    """;



    public static final String GET_SALE_BY_MEDICATION = "SELECT * FROM SALE WHERE saleMedicationId = :saleMedicationId";

    public static final String GET_TOTAL_REVENUE_GENERATED_BY_MEDICATIONS = "SELECT saleMedicationId,medicationName,SUM(saleTotalPrice) AS 'totalRevenue' FROM Sale LEFT JOIN Medication ON medicationId = saleMedicationId LEFT JOIN Hospital ON hospitalId=medicationHospitalId WHERE medicationHospitalId = :medicationHospitalId GROUP BY saleMedicationId, medicationName,hospitalName";

//    public static final String GET_TOTAL_REVENUE_GENERATED_BY_MEDICATION = "SELECT saleMedicationId,SUM(saleTotalPrice) AS 'Total Revenue' FROM Sale WHERE saleMedicationId = :saleMedicationId";

    public static final String GET_SALE_BY_PATIENT = "SELECT * FROM SALE WHERE salePatientId = :salePatientId";

//    public static final String GET_SALE_BY_HOSPITAL_ID = """
//        SELECT H.hospitalName, S.*
//        FROM Sale S
//        LEFT JOIN Medication M ON M.medicationId = S.saleMedicationId
//        LEFT JOIN Hospital H ON M.medicationHospitalId = H.hospitalId
//        WHERE H.hospitalId = :hospitalId
//    """;
}