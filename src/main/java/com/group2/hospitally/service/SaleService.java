package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Sale;
import com.group2.hospitally.model.request.Sale.CreateSaleRequest;
import com.group2.hospitally.repository.Interface.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.getAllSales();
    }

    public Sale getSaleById(int saleId) {
        return saleRepository.getSaleById(saleId);
    }

    public Sale createSale(CreateSaleRequest request) {
        Gson gson = new Gson();
        Sale sale = gson.fromJson(gson.toJson(request), Sale.class);
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