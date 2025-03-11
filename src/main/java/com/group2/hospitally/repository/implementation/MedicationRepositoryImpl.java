package com.group2.hospitally.repository.implementation;

import com.group2.hospitally.mapper.MedicationRowMapper;
import com.group2.hospitally.model.entity.Medication;
import com.group2.hospitally.repository.Interface.MedicationRepository;
import com.group2.hospitally.repository.query.MedicationQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicationRepositoryImpl implements MedicationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MedicationRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Medication getMedicationById(int medicationId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("medicationId", medicationId);
        return jdbcTemplate.queryForObject(MedicationQuery.GET_MEDICATION_BY_ID, parameterSource, new MedicationRowMapper());
    }

    @Override
    public List<Medication> getMedicationByHospitalId(int medicationHospitalId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("medicationHospitalId", medicationHospitalId);
        return jdbcTemplate.query(MedicationQuery.GET_MEDICATION_BY_HOSPITAL_ID, parameterSource, new MedicationRowMapper());
    }

    @Override
    public List<Medication> getMedicationByType(int medicationHospitalId, String medicationType) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("medicationHospital", medicationHospitalId).addValue("medicationType", medicationType);
        return jdbcTemplate.query(MedicationQuery.GET_MEDICATION_BY_TYPE, parameterSource, new MedicationRowMapper());
    }

    @Override
    public List<Medication> getAllMedications() {
        return jdbcTemplate.query(MedicationQuery.GET_ALL_MEDICATIONS, new MedicationRowMapper());
    }

    public void updateStock(int medicationId, int stockQuantity) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("medicationId", medicationId)
                .addValue("stockQuantity", stockQuantity);

        jdbcTemplate.update(MedicationQuery.UPDATE_STOCK_QUANTITY, parameterSource);
    }

    @Override
    public Medication createMedication(Medication medication) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("medicationHospitalId", medication.getMedicationHospitalId())
                .addValue("medicationName", medication.getMedicationName())
                .addValue("medicationType", medication.getMedicationType())
                .addValue("stockQuantity", medication.getStockQuantity())
                .addValue("medicationPrice", medication.getMedicationPrice())
                .addValue("medicationStatus", medication.getMedicationStatus());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(MedicationQuery.INSERT_MEDICATION, parameterSource, keyHolder, new String[]{"medicationId"});

        // Get the generated ID and set it in the patient object
        int medicationId = keyHolder.getKey().intValue();
        medication.setMedicationId(medicationId);

        return medication;

    }

    @Override
    public Medication updateMedication(Medication medication) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("medicationId", medication.getMedicationId())
                .addValue("medicationHospitalId", medication.getMedicationHospitalId())
                .addValue("medicationName", medication.getMedicationName())
                .addValue("medicationType", medication.getMedicationType())
                .addValue("stockQuantity", medication.getStockQuantity())
                .addValue("medicationPrice", medication.getMedicationPrice())
                .addValue("medicationStatus", medication.getMedicationStatus());

        jdbcTemplate.update(MedicationQuery.UPDATE_MEDICATION_BY_ID, parameterSource);

        return getMedicationById(medication.getMedicationId());
    }

    @Override
    public int deleteMedicationById(int medicationId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("medicationId", medicationId);
        return jdbcTemplate.update(MedicationQuery.DELETE_MEDICATION_BY_ID, parameterSource);
    }
}