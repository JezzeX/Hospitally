package com.group2.hospitally.repository.query;

public class AppointmentQuery {
    public static final String INSERT_APPOINTMENT = """
            INSERT INTO Appointment(patientId, staffId, appointmentDate, appointmentTime, appointmentStatus, appointmentCreatedAt, appointmentUpdatedAt)
            VALUES (:patientId, :staffId, :appointmentDate, :appointmentTime, :appointmentStatus, NOW(), NOW())
            """;

    public static final String GET_ALL_APPOINTMENTS = "SELECT * FROM Appointment";

    public static final String GET_APPOINTMENT_BY_ID = "SELECT * FROM Appointment WHERE appointmentId = :appointmentId";

    public static final String UPDATE_APPOINTMENT_BY_ID = """
            UPDATE Appointment
            SET patientId = :patientId,
                staffId = :staffId,
                appointmentDate = :appointmentDate,
                appointmentTime = :appointmentTime,
                appointmentStatus = :appointmentStatus,
                appointmentUpdatedAt = NOW()
            WHERE appointmentId = :appointmentId
            """;

    public static final String DELETE_APPOINTMENT_BY_ID = "DELETE FROM Appointment WHERE appointmentId = :appointmentId";
}
