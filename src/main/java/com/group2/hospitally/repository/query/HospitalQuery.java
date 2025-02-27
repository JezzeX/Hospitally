package com.group2.hospitally.repository.query;

public class HospitalQuery {
    public static final String INSERT_HOSPITAL = """
            INSERT INTO Hospital(hospitalName, hospitalAddress, hospitalPhoneNo, hospitalEmail, hospitalCreatedAt, hospitalUpdatedAt)
            VALUES (:hospitalName, :hospitalAddress, :hospitalPhoneNo, :hospitalEmail, GETDATE(), GETDATE())
            """;

    public static final String GET_ALL_HOSPITALS =
            "SELECT * FROM Hospital WHERE hospitalStatus = 'Active'";

    public static final String GET_HOSPITAL_BY_ID =
            "SELECT * FROM Hospital WHERE hospitalId = :hospitalId AND hospitalStatus = 'Active'";

    public static final String UPDATE_HOSPITAL_BY_ID = """
            UPDATE Hospital
            SET hospitalName = :hospitalName,
                hospitalAddress = :hospitalAddress,
                hospitalPhoneNo = :hospitalPhoneNo,
                hospitalEmail = :hospitalEmail,
                hospitalUpdatedAt = GETDATE()
            WHERE hospitalId = :hospitalId AND hospitalStatus = 'Active'";"
            """;

//    public static final String DELETE_HOSPITAL_BY_ID =
//            "DELETE FROM Hospital WHERE hospitalId = :hospitalId";

    public static final String DELETE_HOSPITAL_BY_ID = """
            UPDATE Hospital
            SET hospitalStatus = 'Inactive',
            hospitalUpdatedAt = GETDATE()
            WHERE hospitalId = :hospitalId AND hospitalStatus = 'Active'";"
           """;


}
