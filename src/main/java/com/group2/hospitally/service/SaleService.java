package com.group2.hospitally.service;

import com.group2.hospitally.model.entity.Medication;
import com.group2.hospitally.model.entity.Patient;
import com.group2.hospitally.model.entity.Sale;
import com.group2.hospitally.model.request.Sale.CreateSaleRequest;
import com.group2.hospitally.model.response.RevenueResponse;
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
    private final PatientService patientService;

    @Autowired
    public SaleService(SaleRepository saleRepository, MedicationService medicationService, PatientService patientService) {
        this.saleRepository = saleRepository;
        this.medicationService = medicationService;
        this.patientService = patientService;
    }

    public List<Sale> getAllSales() {
        try {
            return saleRepository.getAllSales();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error getting all sales",e);
        }

    }

    public List<RevenueResponse> getTotalRevenueGeneratedByMedications(int hospitalId) {
//        try {
        return saleRepository.getTotalRevenueGeneratedByMedications(hospitalId);
//        }catch (Exception e){
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error getting all sales",e);
//        }

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

    public List<Sale> getSaleByMedicationId(int saleMedicationId) {
        try{
            List<Sale> sale = saleRepository.getSaleByMedicationId(saleMedicationId);
            if(sale==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sale not found");
            }
            return sale;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error getting sale",e);
        }
    }

    public List<Sale> getSaleByPatientId(int salePatientId) {
        try {
            List<Sale> sale = saleRepository.getSaleByPatientId(salePatientId);
            if(sale==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sale not found");
            }
            return sale;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error getting sale",e);
        }
    }

//    public List<Sale> getSaleByHospital(int hospitalId) {
//        try {
//            List<Sale> sale = saleRepository.getSaleByHospital(hospitalId);
//            if (sale == null) {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found");
//            }
//            return sale;
//        }catch (Exception e){
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error getting sale",e);
//        }
//    }

//    public Sale createSale(CreateSaleRequest request) {
//        try {
//            Medication medication = medicationService.getMedicationById(request.getSaleMedicationId());
//
//            Patient patient = patientService.getPatientById(request.getSalePatientId());
//            if (medication == null || patient == null) {
//                throw new RuntimeException("Medication or patient with id " + request.getSaleMedicationId() + " not found");
//            }
//
//            Sale sale = new Sale();
//            sale.setSaleMedicationId(request.getSaleMedicationId());
//            sale.setSalePatientId(request.getSalePatientId());
//            sale.setSaleQuantity(request.getSaleQuantity());
//            sale.setSaleTotalPrice(request.getSaleTotalPrice());//I should remove this. Create a query for handling total sale too
//            sale.setSaleDate(LocalDate.now());
//            sale.setSaleCreatedAt(LocalDateTime.now());
//
//            return saleRepository.createSale(sale);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error creating sale",e);
//        }
//    }

    public Sale createSale(CreateSaleRequest request) {
        try {
            Medication medication = medicationService.getMedicationById(request.getSaleMedicationId());
            Patient patient = patientService.getPatientById(request.getSalePatientId());

            if (medication == null || patient == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medication or patient not found.");
            }

            if (request.getSaleQuantity() > medication.getStockQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock for this medication.");
            }

            double totalPrice = medication.getMedicationPrice() * request.getSaleQuantity();

            Sale sale = new Sale();
            sale.setSaleMedicationId(request.getSaleMedicationId());
            sale.setSalePatientId(request.getSalePatientId());
            sale.setSaleQuantity(request.getSaleQuantity());
            sale.setSaleTotalPrice(totalPrice);
            sale.setSaleDate(LocalDate.now());
            sale.setSaleCreatedAt(LocalDateTime.now());

            Sale createdSale = saleRepository.createSale(sale);

            medication.setStockQuantity(medication.getStockQuantity() - request.getSaleQuantity());
            medicationService.updateMedicationStock(medication);

            return createdSale;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating sale", e);
        }
    }

}