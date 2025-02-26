package com.group2.hospitally.repository.implementation;

import com.group2.hospitally.mapper.HospitalRowMapper;
import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.repository.Interface.HospitalRepository;
import com.group2.hospitally.repository.query.HospitalQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HospitalRepositoryImpl implements HospitalRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public HospitalRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hospital getHospitalById(int hospitalId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("hospitalId", hospitalId);
        return jdbcTemplate.queryForObject(HospitalQuery.GET_HOSPITAL_BY_ID, parameterSource, new HospitalRowMapper());
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return jdbcTemplate.query(HospitalQuery.GET_ALL_HOSPITALS, new HospitalRowMapper());
    }

    @Override
    public Hospital createHospital(Hospital hospital) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("hospitalName", hospital.getHospitalName())
                .addValue("hospitalAddress", hospital.getHospitalAddress())
                .addValue("hospitalPhoneNo", hospital.getHospitalPhoneNo())
                .addValue("hospitalEmail", hospital.getHospitalEmail())
                .addValue("hospitalStatus", hospital.getHospitalStatus());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(HospitalQuery.INSERT_HOSPITAL, parameterSource, keyHolder, new String[]{"hospital_id"});

        int hospitalId = keyHolder.getKey().intValue();
        hospital.setHospitalId(hospitalId);
        return hospital;
    }

    @Override
    public Hospital updateHospital(Hospital hospital) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("hospitalId", hospital.getHospitalId())
                .addValue("hospitalName", hospital.getHospitalName())
                .addValue("hospitalAddress", hospital.getHospitalAddress())
                .addValue("hospitalPhoneNo", hospital.getHospitalPhoneNo())
                .addValue("hospitalEmail", hospital.getHospitalEmail())
                .addValue("hospitalStatus", hospital.getHospitalStatus());

        jdbcTemplate.update(HospitalQuery.UPDATE_HOSPITAL_BY_ID, parameterSource);

        // Retrieve and return the updated hospital
        return getHospitalById(hospital.getHospitalId());
    }

    @Override
    public int deleteHospitalById(int hospitalId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("hospitalId", hospitalId);
        return jdbcTemplate.update(HospitalQuery.DELETE_HOSPITAL_BY_ID, parameterSource);
    }
}
