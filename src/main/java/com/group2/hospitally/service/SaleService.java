package com.group2.hospitally.service;

import com.google.gson.Gson;
import com.group2.hospitally.model.entity.Medication;
import com.group2.hospitally.model.entity.Sale;
import com.group2.hospitally.model.request.Sale.CreateSaleRequest;
import com.group2.hospitally.repository.Interface.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            return saleRepository.getAllSales();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error getting all sales",e);
        }

    }

    public Sale getSaleById(int saleId) {
        try {
            Sale sale = saleRepository.getSaleById(saleId);
            if(sale==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sale not found");
            }
            return sale;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error getting sale",e);
        }
    }

    public List<Sale> getSaleByMedicationId(int medicationId) {
        try{
            List<Sale> sale = saleRepository.getSaleByMedicationId(medicationId);
            if(sale==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sale not found");
            }
            return sale;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error getting sale",e);
        }
    }

    public List<Sale> getSaleByPatientId(int patientId) {
        try {
            List<Sale> sale = saleRepository.getSaleByPatientId(patientId);
            if(sale==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sale not found");
            }
            return sale;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error getting sale",e);
        }
    }

    public List<Sale> getSaleByHospital(int hospitalId) {
        try {
            List<Sale> sale = saleRepository.getSaleByHospital(hospitalId);
            if (sale == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found");
            }
            return sale;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error getting sale",e);
        }
    }

    public Sale createSale(CreateSaleRequest request) {
        try {
            Medication medication = medicationService.getMedicationById(request.getMedicationId());
            if (medication == null) {
                throw new RuntimeException("Medication with id " + request.getMedicationId() + " not found");
            }

            Sale sale = new Sale();

            sale.setMedicationId(request.getMedicationId());
            sale.setPatientId(request.getPatientId());
            sale.setSaleQuantity(request.getSaleQuantity());
            sale.setSaleTotalPrice(request.getSaleTotalPrice());
            sale.setSaleDate(LocalDate.now());
            sale.setSaleCreatedAt(LocalDateTime.now());

            return saleRepository.createSale(sale);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error creating sale",e);
        }
    }

}