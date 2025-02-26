package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Appointment;
import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.entity.Patient;
import com.group2.hospitally.model.entity.Staff;
import com.group2.hospitally.model.request.appointment.CreateAppointmentRequest;
import com.group2.hospitally.model.request.appointment.UpdateAppointmentRequest;
import com.group2.hospitally.repository.Interface.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final StaffService staffService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientService patientService, StaffService staffService) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.staffService = staffService;
    }

    public Appointment getAppointmentById(int appointmentId) {
        try{
            Appointment appointment = appointmentRepository.getAppointmentById(appointmentId);
            if (appointment == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment with id " + appointmentId + " not found");
            }
            return appointment;

        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving appointment", e);
        }
    }

    public List<Appointment> getAllAppointments() {
        try {
            return appointmentRepository.getAllAppointments();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving appointments", e);
        }
    }


    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        try{
            return appointmentRepository.getAppointmentsByPatientId(patientId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving appointments by patient ID", e);
        }

    }

    public List<Appointment> getAppointmentsByStaffId(int staffId) {
        try {
            return appointmentRepository.getAppointmentsByStaffId(staffId);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving appointments by staff ID", e);
        }

    }

    public Appointment createAppointment(CreateAppointmentRequest request) {
        try{
            Patient patient = patientService.getPatientById(request.getPatientId());
            if (patient == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
            }

            Staff staff = staffService.getStaffById(request.getStaffId());
            if (staff == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found");
            }
            Appointment appointment = Appointment.builder()
                    .patientId(request.getPatientId())
                    .staffId(request.getStaffId())
                    .appointmentDate(request.getAppointmentDate())
                    .appointmentTime(request.getAppointmentTime())
                    .build();

            return appointmentRepository.createAppointment(appointment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating appointment", e);
        }


    }

    public Appointment updateAppointment(int appointmentId, UpdateAppointmentRequest request) {
        try{
            // Retrieve the appointment to check if it exists
            Appointment existingAppointment = appointmentRepository.getAppointmentById(appointmentId);
            if (existingAppointment == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found");
            }

            existingAppointment.setAppointmentDate(request.getAppointmentDate());
            existingAppointment.setAppointmentTime(request.getAppointmentTime());

            return appointmentRepository.updateAppointment(existingAppointment);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating appointment", e);
        }

    }

    public void deleteAppointmentById(int appointmentId) {
        try{
            Appointment existingAppointment = appointmentRepository.getAppointmentById(appointmentId);
            if (existingAppointment == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found");
            }
            appointmentRepository.deleteAppointmentById(appointmentId);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting appointment", e);
        }
    }
}
