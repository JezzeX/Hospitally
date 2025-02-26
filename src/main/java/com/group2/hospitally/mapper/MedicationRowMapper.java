package com.group2.hospitally.mapper;

import com.group2.hospitally.model.entity.Medication;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicationRowMapper implements RowMapper<Medication> {

    @Override
    public Medication mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Medication.builder()
                .medicationId(rs.getInt("medicationId"))
                .hospitalId(rs.getInt("hospitalId"))
                .medicationName(rs.getString("medicationName"))
                .medicationType(rs.getString("medicationType"))
                .stockQuantity(rs.getInt("stockQuantity"))
                .medicationPrice(rs.getDouble("medicationPrice"))
                .medicationStatus(rs.getString("medicationStatus"))
                .medicationCreatedAt(rs.getTimestamp("medicationCreatedAt").toLocalDateTime())
                .medicationUpdatedAt(rs.getTimestamp("medicationUpdatedAt").toLocalDateTime())
                .build();
    }
}