package com.group2.hospitally.mapper;

import com.group2.hospitally.model.entity.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRowMapper implements RowMapper<Staff> {

    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Staff.builder()
                .staffId(rs.getInt("staffId"))
                .staffName(rs.getString("staffName"))
                .staffContact(rs.getString("staffContact"))
                .staffRole(rs.getString("staffRole"))
                .staffDepartment(rs.getString("staffDepartment"))
                .staffStatus(rs.getString("staffStatus"))
                .staffCreatedAt(rs.getTimestamp("staffCreatedAt").toLocalDateTime())
                .staffUpdatedAt(rs.getTimestamp("staffUpdatedAt").toLocalDateTime())
                .build();
    }
}
