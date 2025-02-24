package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Appointment;
import com.group2.hospitally.model.request.appointment.CreateAppointmentRequest;
import com.group2.hospitally.repository.Interface.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public Appointment getAppointmentById(int appointmentId) {
        return appointmentRepository.getAppointmentById(appointmentId);
    }

    public Appointment createAppointment(CreateAppointmentRequest request) {
        Gson gson = new Gson();
        Appointment appointment = gson.fromJson(gson.toJson(request), Appointment.class);
        return appointmentRepository.createAppointment(appointment);
    }

    public Appointment updateAppointment(int appointmentId, CreateAppointmentRequest request) {
        // Retrieve the appointment to check if it exists
        Appointment existingAppointment = appointmentRepository.getAppointmentById(appointmentId);
        if (existingAppointment == null) {
            throw new RuntimeException("Appointment with ID " + appointmentId + " not found");
        }

        // Update appointment details
        existingAppointment.setPatientId(request.getPatientId());
        existingAppointment.setStaffId(request.getStaffId());
        existingAppointment.setAppointmentDate(request.getAppointmentDate());
        existingAppointment.setAppointmentTime(request.getAppointmentTime());
//        existingAppointment.setAppointmentStatus(request.getAppointmentStatus());

        // Save the updated appointment
        return appointmentRepository.updateAppointment(existingAppointment);
    }

    public void deleteAppointmentById(int appointmentId) {
        appointmentRepository.deleteAppointmentById(appointmentId);
    }
}
