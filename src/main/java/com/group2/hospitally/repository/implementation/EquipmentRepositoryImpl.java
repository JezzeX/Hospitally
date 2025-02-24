package com.group2.hospitally.repository.implementation;

import com.group2.hospitally.mapper.EquipmentRowMapper;
import com.group2.hospitally.model.entity.Equipment;
import com.group2.hospitally.repository.Interface.EquipmentRepository;
import com.group2.hospitally.repository.query.EquipmentQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EquipmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Equipment getEquipmentById(int equipmentId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("equipmentId", equipmentId);
        return jdbcTemplate.queryForObject(EquipmentQuery.GET_EQUIPMENT_BY_ID, parameterSource, new EquipmentRowMapper());
    }


    @Override
    public List<Equipment> getAllEquipments() {
        return jdbcTemplate.query(EquipmentQuery.GET_ALL_EQUIPMENT, new EquipmentRowMapper());
    }

    @Override
    public Equipment createEquipment(Equipment equipment) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("hospitalId", equipment.getHospitalId())
                .addValue("equipmentName", equipment.getEquipmentName())
                .addValue("equipmentType", equipment.getEquipmentType())
                .addValue("equipmentStatus", equipment.getEquipmentStatus())
                .addValue("assignedDepartment", equipment.getAssignedDepartment());

        int id = jdbcTemplate.update(EquipmentQuery.INSERT_EQUIPMENT, parameterSource);

        // Retrieve and return the newly created equipment
        MapSqlParameterSource parameterSource2 = new MapSqlParameterSource("equipmentId", id);
        return jdbcTemplate.queryForObject(EquipmentQuery.GET_EQUIPMENT_BY_ID, parameterSource2, new EquipmentRowMapper());
    }

    @Override
    public Equipment updateEquipment(Equipment equipment) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("equipmentId", equipment.getEquipmentId())
                .addValue("hospitalId", equipment.getHospitalId())
                .addValue("equipmentName", equipment.getEquipmentName())
                .addValue("equipmentType", equipment.getEquipmentType())
                .addValue("equipmentStatus", equipment.getEquipmentStatus())
                .addValue("assignedDepartment", equipment.getAssignedDepartment());

        jdbcTemplate.update(EquipmentQuery.UPDATE_EQUIPMENT_BY_ID, parameterSource);

        // Retrieve and return the updated equipment
        return getEquipmentById(equipment.getEquipmentId());
    }

    @Override
    public int deleteEquipmentById(int equipmentId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("equipmentId", equipmentId);
        return jdbcTemplate.update(EquipmentQuery.DELETE_EQUIPMENT_BY_ID, parameterSource);
    }
}
