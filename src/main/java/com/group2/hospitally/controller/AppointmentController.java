package com.group2.hospitally.controller;

import com.group2.hospitally.model.entity.Appointment;
import com.group2.hospitally.model.request.appointment.CreateAppointmentRequest;
import com.group2.hospitally.model.request.appointment.UpdateAppointmentRequest;
import com.group2.hospitally.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // gett all apointments of a for a patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAllAppointmentsByPatientId(@PathVariable int patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

//    get all the appointment s for a patient
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<Appointment>> getAllAppointmentsByStaffId(@PathVariable int staffId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByStaffId(staffId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Appointment> createAppointment(@RequestBody @Valid CreateAppointmentRequest request) {
        Appointment appointment = appointmentService.createAppointment(request);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

//    changing the update to reschedule
    @PutMapping("/update/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int appointmentId, @RequestBody @Valid UpdateAppointmentRequest request) {
        Appointment updatedAppointment = appointmentService.updateAppointment(appointmentId, request);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int appointmentId) {
        appointmentService.deleteAppointmentById(appointmentId);
        return new ResponseEntity<>("Appointment deleted successfully", HttpStatus.OK);
    }
}
