package com.group2.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Hospital {
        private int hospitalId;
        private String hospitalName;
        private String hospitalAddress;
        private String hospitalPhoneNo;
        private String hospitalEmail;
        private String hospitalStatus;
        private LocalDateTime hospitalCreatedAt;
        private LocalDateTime hospitalUpdatedAt;

}
