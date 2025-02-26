package com.group2.hospitally.mapper;

import com.group2.hospitally.model.entity.Appointment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentRowMapper implements RowMapper<Appointment> {

    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Appointment.builder()
                .appointmentId(rs.getInt("appointmentId"))
                .patientId(rs.getInt("patientId"))
                .staffId(rs.getInt("staffId"))
                .appointmentDate(rs.getString("appointmentDate"))
                .appointmentTime(rs.getString("appointmentTime"))
                .appointmentStatus(rs.getString("appointmentStatus"))
                .appointmentCreatedAt(rs.getTimestamp("appointmentCreatedAt").toLocalDateTime())
                .appointmentUpdatedAt(rs.getTimestamp("appointmentUpdatedAt").toLocalDateTime())
                .build();
    }
}
