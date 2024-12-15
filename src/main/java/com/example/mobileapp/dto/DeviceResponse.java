package com.example.mobileapp.dto;

import com.example.mobileapp.audit.AuditMetadata;
import com.example.mobileapp.model.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DeviceResponse {
    private Long id;
    private String manufacturer;
    private String model;
    private String colorCode;
    private Integer storageCapacity;
    private BigDecimal price;
    private DeviceStatus status;
    private AuditMetadata auditMetadata;
}