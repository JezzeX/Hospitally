package com.group2.hospitally.repository.implementation;

import com.group2.hospitally.mapper.AppointmentRowMapper;
import com.group2.hospitally.model.entity.Appointment;
import com.group2.hospitally.repository.Interface.AppointmentRepository;
import com.group2.hospitally.repository.query.AppointmentQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AppointmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("appointmentId", appointmentId);
        return jdbcTemplate.queryForObject(AppointmentQuery.GET_APPOINTMENT_BY_ID, parameterSource, new AppointmentRowMapper());
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return jdbcTemplate.query(AppointmentQuery.GET_ALL_APPOINTMENTS, new AppointmentRowMapper());
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("patientId", patientId);
        return jdbcTemplate.query(AppointmentQuery.GET_APPOINTMENTS_BY_PATIENT_ID, parameterSource, new AppointmentRowMapper());
    }

    @Override
    public List<Appointment> getAppointmentsByStaffId(int staffId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("staffId", staffId);
        return jdbcTemplate.query(AppointmentQuery.GET_APPOINTMENTS_BY_STAFF_ID, parameterSource, new AppointmentRowMapper());
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("patientId", appointment.getPatientId())
                .addValue("staffId", appointment.getStaffId())
                .addValue("appointmentDate", appointment.getAppointmentDate())
                .addValue("appointmentTime", appointment.getAppointmentTime())
                .addValue("appointmentStatus", appointment.getAppointmentStatus());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(AppointmentQuery.INSERT_APPOINTMENT, parameterSource, keyHolder, new String[]{"appointment_id"} );

        int appointmentId = keyHolder.getKey().intValue();
        appointment.setAppointmentId(appointmentId);
        return appointment;
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("appointmentId", appointment.getAppointmentId())
                .addValue("patientId", appointment.getPatientId())
                .addValue("staffId", appointment.getStaffId())
                .addValue("appointmentDate", appointment.getAppointmentDate())
                .addValue("appointmentTime", appointment.getAppointmentTime())
                .addValue("appointmentStatus", appointment.getAppointmentStatus());

        jdbcTemplate.update(AppointmentQuery.UPDATE_APPOINTMENT_BY_ID, parameterSource);

        // Retrieve and return the updated appointment
        return getAppointmentById(appointment.getAppointmentId());
    }

    @Override
    public int deleteAppointmentById(int appointmentId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("appointmentId", appointmentId);
        return jdbcTemplate.update(AppointmentQuery.DELETE_APPOINTMENT_BY_ID, parameterSource);
    }
}
