package com.group2.hospitally.repository.query;

public class HospitalQuery {
    public static final String INSERT_HOSPITAL = """
            INSERT INTO Hospital(hospitalName, hospitalAddress, hospitalPhoneNo, hospitalEmail, hospitalStatus, hospitalCreatedAt, hospitalUpdatedAt)
            VALUES (:hospitalName, :hospitalAddress, :hospitalPhoneNo, :hospitalEmail, :hospitalStatus, NOW(), NOW())
            """;

    public static final String GET_ALL_HOSPITALS = "SELECT * FROM Hospital";

    public static final String GET_HOSPITAL_BY_ID = "SELECT * FROM Hospital WHERE hospitalId = :hospitalId";

    public static final String UPDATE_HOSPITAL_BY_ID = """
            UPDATE Hospital
            SET hospitalName = :hospitalName,
                hospitalAddress = :hospitalAddress,
                hospitalPhoneNo = :hospitalPhoneNo,
                hospitalEmail = :hospitalEmail,
                hospitalStatus = :hospitalStatus,
                hospitalUpdatedAt = NOW()
            WHERE hospitalId = :hospitalId
            """;

    public static final String DELETE_HOSPITAL_BY_ID = "DELETE FROM Hospital WHERE hospitalId = :hospitalId";
}
