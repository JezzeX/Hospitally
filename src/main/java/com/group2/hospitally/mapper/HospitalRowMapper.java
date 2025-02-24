package com.group2.hospitally.mapper;

import com.group2.hospitally.model.entity.Hospital;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalRowMapper implements RowMapper<Hospital> {

    @Override
    public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException{
        return Hospital.builder()
                .hospitalId(rs.getInt("hospitalId"))
                .hospitalName(rs.getString("hospitalName"))
                .hospitalAddress("hospitalAddress")
                .hospitalPhoneNo("hospitalPhoneNo")
                .hospitalEmail("hospitalEmail")
                .hospitalStatus("hospitalStatus")
                .hospitalCreatedAt("hospitalCreatedAt")
                .hospitalUpdatedAt("hospitalUpdatedAt")
                .build();
    }
}
