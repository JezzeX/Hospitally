package com.group2.hospitally.mapper;

import com.group2.hospitally.model.entity.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Patient.builder()
                .patientId(rs.getInt("patientId"))
                .hospitalId(rs.getInt("hospitalId"))
                .patientName(rs.getString("patientName"))
                .patientDob(rs.getDate("patientDob").toLocalDate())
                .patientGender(rs.getString("patientGender"))
                .patientContact(rs.getString("patientContact"))
                .patientAddress(rs.getString("patientAddress"))
                .patientMedicalHistory(rs.getString("patientMedicalHistory"))
                .patientStatus(rs.getString("patientStatus"))
                .patientCreatedAt(rs.getTimestamp("patientCreatedAt").toLocalDateTime())
                .patientUpdatedAt(rs.getTimestamp("patientUpdatedAt").toLocalDateTime())
                .build();
    }
}

