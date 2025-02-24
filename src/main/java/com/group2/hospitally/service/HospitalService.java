package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.request.Hospital.CreateHospitalRequest;
import com.group2.hospitally.repository.Interface.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.getAllHospitals();
    }

    public Hospital getHospitalById(int hospitalId) {
        return hospitalRepository.getHospitalById(hospitalId);
    }

    public Hospital createHospital(CreateHospitalRequest request) {
        Gson gson = new Gson();
        Hospital hospital = gson.fromJson(gson.toJson(request), Hospital.class);
        return hospitalRepository.createHospital(hospital);
    }

    public Hospital updateHospital(int hospitalId, CreateHospitalRequest request) {
        // Retrieve the hospital to check if it exists
        Hospital existingHospital = hospitalRepository.getHospitalById(hospitalId);
        if (existingHospital == null) {
            throw new RuntimeException("Hospital with ID " + hospitalId + " not found");
        }

        // Update the hospital details
        existingHospital.setHospitalName(request.getHospitalName());
        existingHospital.setHospitalAddress(request.getHospitalAddress());
        existingHospital.setHospitalPhoneNo(request.getHospitalPhoneNo());
        existingHospital.setHospitalEmail(request.getHospitalEmail());

        // Save the updated hospital
        return hospitalRepository.updateHospital(existingHospital);
    }

    public void deleteHospitalById(int hospitalId) {
        hospitalRepository.deleteHospitalById(hospitalId);
    }
}
