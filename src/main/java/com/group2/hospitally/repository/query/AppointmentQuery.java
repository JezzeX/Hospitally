package com.group2.hospitally.repository.query;

public class AppointmentQuery {
    public static final String INSERT_APPOINTMENT = """
            INSERT INTO Appointment(patientId, staffId, appointmentDate, appointmentTime, appointmentCreatedAt, appointmentUpdatedAt)
            VALUES (:patientId, :staffId, :appointmentDate, :appointmentTime, GETDATE(), GETDATE())
            """;

    public static final String GET_ALL_APPOINTMENTS = "SELECT * FROM Appointment";

    public static final String GET_APPOINTMENT_BY_ID = "SELECT * FROM Appointment WHERE appointmentId = :appointmentId";

    public static final String GET_APPOINTMENTS_BY_PATIENT_ID = "SELECT * FROM Appointment WHERE patientId = :patientId";

    public static final String GET_APPOINTMENTS_BY_STAFF_ID = "SELECT * FROM Appointment WHERE staffId = :staffId";

    public static final String UPDATE_APPOINTMENT_BY_ID = """
            UPDATE Appointment
            SET appointmentDate = :appointmentDate,
                appointmentTime = :appointmentTime,
                appointmentUpdatedAt = GETDATE()
            WHERE appointmentId = :appointmentId
            """;

//    public static final String GET_SALE_BY_HOSPITAL_ID = """
//        SELECT H.hospitalName, S.*
//        FROM Sale S
//        LEFT JOIN Medication M ON M.medicationId = S.medicationId
//        LEFT JOIN Hospital H ON M.hospitalId = H.hospitalId
//        WHERE H.hospitalId = :hospitalId
//    """;



    public static final String DELETE_APPOINTMENT_BY_ID = "DELETE FROM Appointment WHERE appointmentId = :appointmentId";
}
