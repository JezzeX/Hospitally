package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.request.Hospital.CreateHospitalRequest;
import com.group2.hospitally.model.request.Hospital.UpdateHospitalRequest;
import com.group2.hospitally.repository.Interface.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital getHospitalById(int hospitalId) {
        try{
            Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
            if (hospital == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital with id " + hospitalId + " not found");
            }
            return hospital;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Hospital", e);
        }
    }

    public List<Hospital> getAllHospitals() {
        try{
            return hospitalRepository.getAllHospitals();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Hospitals", e);
        }
    }

    public Hospital createHospital(CreateHospitalRequest request) {
        try {
            Hospital hospital = Hospital.builder()
                    .hospitalName(request.getHospitalName())
                    .hospitalAddress(request.getHospitalAddress())
                    .hospitalPhoneNo(request.getHospitalPhoneNo())
                    .hospitalEmail(request.getHospitalEmail())
                    .build();
            return hospitalRepository.createHospital(hospital);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Creating Hospital", e);
        }

    }

    public Hospital updateHospital(int hospitalId, UpdateHospitalRequest request) {
        try{
            // Retrieve the hospital to check if it exists
            Hospital existingHospital = hospitalRepository.getHospitalById(hospitalId);
            if (existingHospital == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital with id " + hospitalId + " not found");
            }

            // Update the hospital details
            existingHospital.setHospitalName(request.getHospitalName());
            existingHospital.setHospitalAddress(request.getHospitalAddress());
            existingHospital.setHospitalPhoneNo(request.getHospitalPhoneNo());
            existingHospital.setHospitalEmail(request.getHospitalEmail());

            // Save the updated hospital
            return hospitalRepository.updateHospital(existingHospital);

        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Updating Hospital", e);
        }


    }

    public void deleteHospitalById(int hospitalId) {
        try{
            Hospital existingHospital = hospitalRepository.getHospitalById(hospitalId);
            if (existingHospital == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital with id " + hospitalId + " not found");
            }
            hospitalRepository.deleteHospitalById(hospitalId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Deleting Hospital", e);
        }
    }
}
