package com.group2.hospitally.mapper;

import com.group2.hospitally.model.entity.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRowMapper implements RowMapper<Staff> {

    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException{
        return Staff.builder()
                .staffId(rs.getInt("staffId"))
                .staffName(rs.getString("staffName"))
                .staffContact("staffContact")
                .staffRole("staffRole")
                .staffDepartment("staffDepartment")
                .staffStatus("staffStatus")
                .staffCreatedAt("staffCreatedAt")
                .staffUpdatedAt("staffUpdatedAt")
                .build();
    }
}