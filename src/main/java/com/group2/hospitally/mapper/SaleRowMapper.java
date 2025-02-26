package com.group2.hospitally.mapper;

import com.group2.hospitally.model.entity.Sale;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleRowMapper implements RowMapper<Sale> {

    @Override
    public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Sale.builder()
                .saleId(rs.getInt("saleId"))
                .medicationId(rs.getInt("medicationId"))
                .patientId(rs.getInt("patientId"))
                .saleQuantity(rs.getInt("saleQuantity"))
                .saleTotalPrice(rs.getDouble("saleTotalPrice"))
                .saleDate(rs.getDate("saleDate").toLocalDate())
                .saleCreatedAt(rs.getTimestamp("saleCreatedAt").toLocalDateTime())
                .saleUpdatedAt(rs.getTimestamp("saleUpdatedAt").toLocalDateTime())
                .build();
    }
}