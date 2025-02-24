package com.group2.hospitally.mapper;

import com.group2.hospitally.model.entity.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException{
        return Patient.builder()
                .patientId(rs.getInt("patientId"))
                .patientName(rs.getString("patientName"))
                .patientDob("patientDob")
                .patientGender("patientGender")
                .patientContact("patientContact")
                .patientAddress("patientAddress")
                .patientMedicalHistory("patientMedicalHistory")
                .patientStatus("patientStatus")
                .patientCreatedAt("patientCreatedAt")
                .patientUpdatedAt("patientUpdatedAt")
                .build();
    }
}
