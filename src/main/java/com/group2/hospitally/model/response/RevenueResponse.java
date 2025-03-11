package com.group2.hospitally.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RevenueResponse {
private int saleMedicationId;
private String medicationName;
private double totalRevenue;
}
