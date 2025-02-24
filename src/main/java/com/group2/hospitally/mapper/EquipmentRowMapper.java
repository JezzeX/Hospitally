package com.group2.hospitally.mapper;

import com.group2.hospitally.model.entity.Equipment;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentRowMapper implements RowMapper<Equipment> {

    @Override
    public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Equipment.builder()
                .equipmentId(rs.getInt("equipmentId"))
                .hospitalId(rs.getInt("hospitalId"))
                .equipmentName(rs.getString("equipmentName"))
                .equipmentType(rs.getString("equipmentType"))
                .equipmentStatus(rs.getString("equipmentStatus"))
                .assignedDepartment(rs.getString("assignedDepartment"))
                .equipmentCreatedAt(rs.getString("equipmentCreatedAt"))
                .equipmentUpdatedAt(rs.getString("equipmentUpdatedAt"))
                .build();
    }
}