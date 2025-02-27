package com.group2.hospitally.repository.Interface;

import com.group2.hospitally.model.entity.Staff;

import java.util.List;

public interface StaffRepository {
    // get a single staff by id
    Staff getStaffById(int staffId);

    //get all staff on the hms
    List<Staff> getAllStaffs();

    //get all staff in a hospital using hospital id
    List<Staff> getStaffByHospitalId(int hospitalId);

    //Create a new staff on the hms
    Staff createStaff(Staff staff);

    //update staff details
    Staff updateStaff(Staff staff);

    //delete a staff
    int deleteStaffById(int staffId);
}
