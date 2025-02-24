package com.group2.hospitally.model.request.Sale;

import lombok.Data;

@Data
public class UpdateSaleRequest{
        private int medicationId;
        private int patientId; // Nullable
        private int saleQuantity;
        private double saleTotalPrice;
}
