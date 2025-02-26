package com.group2.hospitally.repository.implementation;

import com.group2.hospitally.mapper.EquipmentRowMapper;
import com.group2.hospitally.model.entity.Equipment;
import com.group2.hospitally.repository.Interface.EquipmentRepository;
import com.group2.hospitally.repository.query.EquipmentQuery;
import com.group2.hospitally.repository.query.MedicationQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    public List<Equipment> getEquipmentByHospitalId(int hospitalId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("hospitalId", hospitalId);
        return jdbcTemplate.query(EquipmentQuery.GET_EQUIPMENT_BY_HOSPITAL_ID, parameterSource, new EquipmentRowMapper());
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

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(EquipmentQuery.INSERT_EQUIPMENT, parameterSource, keyHolder, new String[]{"equipmentId"});

        // Get the generated ID and set it in the patient object
        int equipmentId = keyHolder.getKey().intValue();
        equipment.setEquipmentId(equipmentId);

        return equipment;
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
