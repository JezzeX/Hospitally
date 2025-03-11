package com.group2.hospitally.mapper;

import com.group2.hospitally.model.response.RevenueResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RevenueRowMapper implements RowMapper<RevenueResponse> {
    @Override
    public RevenueResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RevenueResponse.builder()
                .saleMedicationId(rs.getInt("saleMedicationId"))
                .medicationName(rs.getString("medicationName"))
                .totalRevenue(rs.getDouble("totalRevenue"))
                .build();
    }
}
