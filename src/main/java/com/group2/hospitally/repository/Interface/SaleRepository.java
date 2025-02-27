package com.group2.hospitally.repository.Interface;

import com.group2.hospitally.model.entity.Sale;
import java.util.List;

public interface SaleRepository {

    // Get a sale by ID
    Sale getSaleById(int saleId);

    // Get a sale by Medication ID
    List<Sale> getSaleByMedicationId(int medicationId);

// Get a sale by Patient ID
    List<Sale> getSaleByPatientId(int patientId);

// Get sale by Hospital
    List<Sale> getSaleByHospital(int hospitalId);

    // Get all sales
    List<Sale> getAllSales();

    // Create a new sale
    Sale createSale(Sale sale);

}