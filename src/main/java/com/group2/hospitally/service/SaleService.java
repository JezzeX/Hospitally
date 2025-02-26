package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Medication;
import com.group2.hospitally.model.entity.Sale;
import com.group2.hospitally.model.request.Sale.CreateSaleRequest;
import com.group2.hospitally.repository.Interface.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final MedicationService medicationService;

    @Autowired
    public SaleService(SaleRepository saleRepository, MedicationService medicationService) {
        this.saleRepository = saleRepository;
        this.medicationService = medicationService;
    }

    public List<Sale> getAllSales() {
        return saleRepository.getAllSales();
    }

    public Sale getSaleById(int saleId) {
        return saleRepository.getSaleById(saleId);
    }

    public List<Sale> getSaleByMedicationId(int medicationId) {
        return saleRepository.getSaleByMedicationId(medicationId);
    }

    public List<Sale> getSaleByPatientId(int patientId) {
        return saleRepository.getSaleByPatientId(patientId);
    }

    public List<Sale> getSaleByHospital(int hospitalId) {
        return saleRepository.getSaleByHospital(hospitalId);
    }

    public Sale createSale(CreateSaleRequest request) {
        Medication medication = medicationService.getMedicationById(request.getMedicationId());
        if (medication == null){
            throw new RuntimeException("Medication with id " + request.getMedicationId() + " not found");
        }

        Sale sale = new Sale();

        sale.setMedicationId(request.getMedicationId());
        sale.setPatientId(request.getPatientId());
        sale.setSaleQuantity(request.getSaleQuantity());
        sale.setSaleTotalPrice(request.getSaleTotalPrice());
        sale.setSaleDate(LocalDate.now());
        sale.setSaleCreatedAt(LocalDateTime.now());
        sale.setSaleUpdatedAt(LocalDateTime.now());

        return saleRepository.createSale(sale);
    }

    public Sale updateSale(int saleId, CreateSaleRequest request) {
        // Retrieve the sale to check if it exists
        Sale existingSale = saleRepository.getSaleById(saleId);
        if (existingSale == null) {
            throw new RuntimeException("Sale with ID " + saleId + " not found");
        }

        // Update the sale details
        existingSale.setMedicationId(request.getMedicationId());
        existingSale.setPatientId(request.getPatientId());
        existingSale.setSaleQuantity(request.getSaleQuantity());
        existingSale.setSaleTotalPrice(request.getSaleTotalPrice());

        // Save the updated sale
        return saleRepository.updateSale(existingSale);
    }

    public void deleteSaleById(int saleId) {
        saleRepository.deleteSaleById(saleId);
    }
}