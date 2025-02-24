package com.group2.hospitally.repository.Interface;

import com.group2.hospitally.model.entity.Staff;

import java.util.List;

public interface StaffRepository {
    // get
    Staff getStaffById(int staffId);

    //get all
    List<Staff> getAllStaffs();

    //Create
    Staff createStaff(Staff staff);

    //update
    Staff updateStaff(Staff staff);

    //delete
    int deleteStaffById(int staffId);
}
