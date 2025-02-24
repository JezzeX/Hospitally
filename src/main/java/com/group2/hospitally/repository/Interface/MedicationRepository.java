package com.group2.hospitally.repository.Interface;

import com.group2.hospitally.model.entity.Medication;
import com.group2.hospitally.model.request.Medication.CreateMedicationRequest;
import com.group2.hospitally.model.request.Medication.UpdateMedicationRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MedicationRepository {
    // Get medication by ID
    Medication getMedicationById(int medicationId);

    // Get all medications
    List<Medication> getAllMedications();

    // Create medication
    Medication createMedication(Medication medication);

    // Update medication
    Medication updateMedication(Medication medication);

    // Delete medication
    int deleteMedicationById(int medicationId);
}