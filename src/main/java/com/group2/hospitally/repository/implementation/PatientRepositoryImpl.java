package com.group2.hospitally.repository.implementation;

import com.group2.hospitally.repository.Interface.PatientRepository;
import com.group2.hospitally.repository.query.PatientQuery;
import com.group2.hospitally.model.entity.Patient;
import com.group2.hospitally.mapper.PatientRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepositoryImpl implements PatientRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PatientRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Patient getPatientById(int patientId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("patientId", patientId);
        return jdbcTemplate.queryForObject(PatientQuery.GET_PATIENT_BY_ID, parameterSource, new PatientRowMapper());
    }

    @Override
    public List<Patient> getAllPatients() {
        return jdbcTemplate.query(PatientQuery.GET_ALL_PATIENTS, new PatientRowMapper());
    }

    @Override
    public List<Patient> getPatientsByHospitalId(int hospitalId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("hospitalId", hospitalId);
        return jdbcTemplate.query(PatientQuery.GET_PATIENTS_BY_HOSPITAL_ID, parameterSource, new PatientRowMapper());
    }

    @Override
    public List<Patient> getPatientsByStatus(String status) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("status", status);
        return jdbcTemplate.query(PatientQuery.GET_PATIENTS_BY_STATUS, parameterSource, new PatientRowMapper());
    }

    @Override
    public Patient createPatient(Patient patient) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("hospitalId", patient.getHospitalId())
                .addValue("patientName", patient.getPatientName())
                .addValue("patientDob", patient.getPatientDob())
                .addValue("patientGender", patient.getPatientGender())
                .addValue("patientContact", patient.getPatientContact())
                .addValue("patientAddress", patient.getPatientAddress())
                .addValue("patientMedicalHistory", patient.getPatientMedicalHistory());
        int id = jdbcTemplate.update(PatientQuery.INSERT_PATIENT, parameterSource);

        // Retrieve and return the newly created patient
        return getPatientById(id);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("patientId", patient.getPatientId())
                .addValue("patientName", patient.getPatientName())
                .addValue("patientDob", patient.getPatientDob())
                .addValue("patientGender", patient.getPatientGender())
                .addValue("patientContact", patient.getPatientContact())
                .addValue("patientAddress", patient.getPatientAddress())
                .addValue("patientMedicalHistory", patient.getPatientMedicalHistory())
                .addValue("patientStatus", patient.getPatientStatus())
                .addValue("patientUpdatedAt", patient.getPatientUpdatedAt());

        jdbcTemplate.update(PatientQuery.UPDATE_PATIENT_BY_ID, parameterSource);
        return getPatientById(patient.getPatientId());
    }

    @Override
    public int deletePatientById(int patientId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("patientId", patientId);
        return jdbcTemplate.update(PatientQuery.DELETE_PATIENT_BY_ID, parameterSource);
    }
}
