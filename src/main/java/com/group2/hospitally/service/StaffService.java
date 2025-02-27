package com.group2.hospitally.service;

import com.group2.hospitally.model.entity.Hospital;
import com.group2.hospitally.model.entity.Patient;
import com.group2.hospitally.model.entity.Staff;
import com.group2.hospitally.model.request.Staff.CreateStaffRequest;
import com.group2.hospitally.repository.Interface.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final HospitalService hospitalService;

    @Autowired
    public StaffService(StaffRepository staffRepository, HospitalService hospitalService) {
        this.staffRepository = staffRepository;
        this.hospitalService = hospitalService;
    }

    public List<Staff> getAllStaffs() {
        try {
            return staffRepository.getAllStaffs();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Staffs", e);
        }
    }

    public Staff getStaffById(int staffId) {
        try {
            Staff staff = staffRepository.getStaffById(staffId);
            if (staff == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff with id " + staffId + " not found");
            }
            return staff;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Staff", e);
        }
    }

    public List<Staff> getStaffByHospitalId(int hospitalId) {
        try {
            Hospital hospital = hospitalService.getHospitalById(hospitalId);
            if (hospital == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital with id " + hospitalId + " not found");
            }
            return staffRepository.getStaffByHospitalId(hospitalId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Staff", e);
        }
    }

    public Staff createStaff(CreateStaffRequest request) {
        try {
            Staff staff = Staff.builder()
                    .staffName(request.getStaffName())
                    .staffRole(request.getStaffRole())
                    .staffContact(request.getStaffContact())
                    .staffDepartment(request.getStaffDepartment())
                    .staffStatus("Active")
                    .staffCreatedAt(LocalDateTime.now())
                    .staffUpdatedAt(LocalDateTime.now())
                    .build();
            return staffRepository.createStaff(staff);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Creating Staff", e);
        }
    }

    public Staff updateStaff(int staffId, CreateStaffRequest request) {
        try {
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
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Updating Staff", e);
        }
    }

    //Delete a staff (Changing their status)
    public void deleteStaffById(int staffId) {
        try {
            Staff existingStaff = getStaffById(staffId);

            if (existingStaff == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff with ID " + staffId + " not found");
            }

            // Soft delete: Update the staff's status to "Inactive"
            existingStaff.setStaffStatus("Inactive");
            existingStaff.setStaffUpdatedAt(LocalDateTime.now());

            // Call the repository to update the staff's status in the database
            int rowsAffected = staffRepository.deleteStaffById(staffId);

            if (rowsAffected == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete staff. No rows were affected.");
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while deleting staff", e);
        }
    }
}
