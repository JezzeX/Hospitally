package com.group2.hospitally.repository.query;

public class AppointmentQuery {
    public static final String INSERT_APPOINTMENT = """
            INSERT INTO Appointment(patientId, staffId, appointmentDate, appointmentTime)
            VALUES (:patientId, :staffId, :appointmentDate, :appointmentTime)
            """;

    public static final String GET_ALL_APPOINTMENTS =
            "SELECT * FROM Appointment WHERE appointmentStatus = 'Active'";

    public static final String GET_APPOINTMENT_BY_ID =
            "SELECT * FROM Appointment WHERE appointmentId = :appointmentId AND appointmentStatus = 'Active'";

    public static final String GET_APPOINTMENTS_BY_PATIENT_ID =
            "SELECT * FROM Appointment WHERE patientId = :patientId AND appointmentStatus = 'Active'";

    public static final String GET_APPOINTMENTS_BY_STAFF_ID =
            "SELECT * FROM Appointment WHERE staffId = :staffId AND appointmentStatus = 'Active'";

    public static final String UPDATE_APPOINTMENT_BY_ID = """
            UPDATE Appointment
            SET appointmentDate = :appointmentDate,
                appointmentTime = :appointmentTime,
                appointmentUpdatedAt = GETDATE()
            WHERE appointmentId = :appointmentId AND appointmentStatus = 'Active'
            """;

//    public static final String DELETE_APPOINTMENT_BY_ID =
//            "DELETE FROM Appointment WHERE appointmentId = :appointmentId";

    public static final String DELETE_APPOINTMENT_BY_ID = """
            UPDATE Appointment
            SET appointmentStatus = 'Inactive'
            WHERE appointmentId = :appointmentId AND appointmentStatus = 'Active'
            """;
}
