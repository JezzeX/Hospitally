package com.group2.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hospital {
        private int hospitalId;
        private String hospitalName;
        private String hospitalAddress;
        private String hospitalPhoneNo;
        private String hospitalEmail;
        private String hospitalStatus;
        private String hospitalCreatedAt;
        private String hospitalUpdatedAt;

}
