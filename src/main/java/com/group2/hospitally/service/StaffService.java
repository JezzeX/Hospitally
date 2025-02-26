package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Staff;
import com.group2.hospitally.model.request.Staff.CreateStaffRequest;
import com.group2.hospitally.repository.Interface.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaffs() {
        return staffRepository.getAllStaffs();
    }

    public Staff getStaffById(int staffId) {
        return staffRepository.getStaffById(staffId);
    }

    public Staff createStaff(CreateStaffRequest request) {
        Gson gson = new Gson();
        Staff staff = gson.fromJson(gson.toJson(request), Staff.class);
        return staffRepository.createStaff(staff);
    }

    public Staff updateStaff(int staffId, CreateStaffRequest request) {
        // Retrieve the staff to check if it exists
        Staff existingStaff = staffRepository.getStaffById(staffId);
        if (existingStaff == null) {
            throw new RuntimeException("Staff with ID " + staffId + " not found");
        }

        // Update the staff details
        existingStaff.setStaffName(request.getStaffName());
        existingStaff.setStaffRole(request.getStaffRole());
        existingStaff.setStaffDepartment(request.getStaffDepartment());
        existingStaff.setStaffContact(request.getStaffContact());

        // Save the updated staff
        return staffRepository.updateStaff(existingStaff);
    }

    public void deleteStaffById(int staffId) {
        staffRepository.deleteStaffById(staffId);
    }
}
