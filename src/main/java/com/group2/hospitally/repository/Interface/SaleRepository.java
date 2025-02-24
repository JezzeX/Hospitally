package com.group2.hospitally.repository.Interface;

import com.group2.hospitally.model.entity.Sale;
import java.util.List;

public interface SaleRepository {

    // Get a sale by ID
    Sale getSaleById(int saleId);

    // Get a sale by Medication ID
    Sale getSaleByMedicationId(int medicationId);

// Get a sale by Patient ID
    Sale getSaleByPatientId(int patientId);

    // Get all sales
    List<Sale> getAllSales();

    // Create a new sale
    Sale createSale(Sale sale);

    // Update an existing sale
    Sale updateSale(Sale sale);

    // Delete a sale by ID
    int deleteSaleById(int saleId);
}