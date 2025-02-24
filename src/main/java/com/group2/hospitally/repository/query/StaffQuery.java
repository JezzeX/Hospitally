package com.group2.hospitally.repository.query;

public class StaffQuery {
    public static final String INSERT_STAFF = """
            INSERT INTO Staff(staffName, staffRole, staffContact, staffDepartment,  staffStatus, staffCreatedAt, staffUpdatedAt)
            VALUES (:staffName, :staffRole, :staffContact, :staffDepartment, :staffStatus, NOW(), NOW())
            """;

    public static final String GET_ALL_STAFFS = "SELECT * FROM Staff";

    public static final String GET_STAFF_BY_ID = "SELECT * FROM Staff WHERE staffId = :staffId";

    public static final String UPDATE_STAFF_BY_ID = """
            UPDATE Staff
            SET staffName = :staffName,
                staffRole = :staffRole,
                staffContact = :staffContact,
                staffDepartment = :staffDepartment,
                staffStatus = :staffStatus,
                staffCreatedAt = NOW(),
                staffUpdatedAt = NOW()
            WHERE staffId = :staffId
            """;

    public static final String DELETE_STAFF_BY_ID = "DELETE FROM Staff WHERE staffId = :staffId";
}
