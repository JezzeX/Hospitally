package com.group2.hospitally.model.request.Sale;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSaleRequest {

    @NotNull
    private int medicationId;

    @NotNull
    private int patientId;

    @NotNull
    private int saleQuantity;

    @NotNull
    private double saleTotalPrice;
}