package com.group2.hospitally.repository.Interface;

import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.request.Hospital.CreateHospitalRequest;
import com.group2.hospitally.model.request.Hospital.UpdateHospitalRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface HospitalRepository {
    // get
    Hospital getHospitalById(int hospitalId);

    //get all
    List<Hospital> getAllHospitals();

    //Create
    Hospital createHospital(Hospital hospital);

    //update
    Hospital updateHospital(Hospital hospital);

    //delete
    int deleteHospitalById(int hospitalId);
}
