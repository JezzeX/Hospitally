package com.group2.hospitally.repository.query;

public class StaffQuery {
    public static final String INSERT_STAFF = """
            INSERT INTO Staff(staffName, staffRole, staffContact, staffDepartment)
            VALUES (:staffName, :staffRole, :staffContact, :staffDepartment)
            """;

    public static final String GET_ALL_STAFFS =
            "SELECT * FROM Staff WHERE staffStatus = 'Active'";

    public static final String GET_STAFF_BY_ID =
            "SELECT * FROM Staff WHERE staffId = :staffId AND staffStatus = 'Active'";

    public static final String GET_STAFF_BY_HOSPITAL_ID =
            "SELECT * FROM Staff WHERE hospitalId = :hospitalId AND staffStatus = 'Active';";

    public static final String UPDATE_STAFF_BY_ID = """
            UPDATE Staff
            SET staffName = :staffName,
                staffRole = :staffRole,
                staffContact = :staffContact,
                staffDepartment = :staffDepartment,
                staffStatus = :staffStatus,
                staffUpdatedAt = GETDATE()
            WHERE staffId = :staffId AND staffStatus = 'Active'
            """;

    public static final String DELETE_STAFF_BY_ID = """
            UPDATE Patient
            SET patientStatus = 'Inactive',
            patientUpdatedAt = GETDATE()
            WHERE patientId = :patientId AND patientStatus = 'Active'""";
}
