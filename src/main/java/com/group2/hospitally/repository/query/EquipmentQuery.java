package com.group2.hospitally.repository.query;

public class EquipmentQuery {

    // Get equipment by ID
    public static final String GET_EQUIPMENT_BY_ID = """
        SELECT * FROM Equipment WHERE equipmentId = :equipmentId AND equipmentStatus = 'Active'
    """;

    // Get all equipment
    public static final String GET_ALL_EQUIPMENT = """
        SELECT * FROM Equipment WHERE equipmentStatus = 'Active'
    """;

    // Insert new equipment
    public static final String INSERT_EQUIPMENT = """
        INSERT INTO Equipment (hospitalId, equipmentName, equipmentType, equipmentStatus, assignedDepartment, equipmentCreatedAt, equipmentUpdatedAt)
        VALUES (:hospitalId, :equipmentName, :equipmentType, COALESCE(:equipmentStatus, 'active'), :assignedDepartment, GETDATE(), GETDATE())
    """;

    // Update equipment by ID
    public static final String UPDATE_EQUIPMENT_BY_ID = """
        UPDATE Equipment
        SET hospitalId = :hospitalId,
            equipmentName = :equipmentName,
            equipmentType = :equipmentType,
            equipmentStatus = :equipmentStatus,
            assignedDepartment = :assignedDepartment,
            equipmentUpdatedAt = GETDATE()
        WHERE equipmentId = :equipmentId
    """;

    // Delete equipment by ID
    public static final String DELETE_EQUIPMENT_BY_ID = """
        UPDATE Equipment
            SET equipmentStatus = 'Deleted',
                equipmentUpdatedAt = GETDATE()
            WHERE equipmentId = :equipmentId
    """;

    public static final String GET_EQUIPMENT_BY_HOSPITAL_ID = "SELECT * FROM Equipment WHERE hospitalId = :hospitalId AND equipmentStatus = 'Active'";
}