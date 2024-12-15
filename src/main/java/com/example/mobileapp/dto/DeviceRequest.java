package com.example.mobileapp.dto;

import com.example.mobileapp.model.DeviceStatus;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DeviceRequest {
    private String manufacturer;
    private String model;
    private String colorCode;
    private Integer storageCapacity;
    private BigDecimal price;
    private DeviceStatus status;
    private boolean available;
}