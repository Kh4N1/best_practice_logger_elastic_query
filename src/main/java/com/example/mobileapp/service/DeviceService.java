package com.example.mobileapp.service;

import com.example.mobileapp.dto.DeviceRequest;
import com.example.mobileapp.model.Device;
import com.example.mobileapp.model.DeviceStatus;
import com.example.mobileapp.repository.DeviceRepository;
import com.example.mobileapp.dto.SearchCriteria;
import com.example.mobileapp.dto.DeviceResponse;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    public void generateSampleData() {
        Device iphone = new Device();
        iphone.setManufacturer("Apple");
        iphone.setModel("iPhone 13");
        iphone.setColorCode("BLACK");
        iphone.setStorageCapacity(128);
        iphone.setPrice(new BigDecimal("999.99"));
        iphone.setStatus(DeviceStatus.IN_STOCK);
        iphone.setAvailable(true);

        Device samsung = new Device();
        samsung.setManufacturer("Samsung");
        samsung.setModel("Galaxy S21");
        samsung.setColorCode("WHITE");
        samsung.setStorageCapacity(256);
        samsung.setPrice(new BigDecimal("899.99"));
        samsung.setStatus(DeviceStatus.NEW_ARRIVAL);
        samsung.setAvailable(true);

        Device xiaomi = new Device();
        xiaomi.setManufacturer("Xiaomi");
        xiaomi.setModel("Redmi Note 10");
        xiaomi.setColorCode("SILVER");
        xiaomi.setStorageCapacity(128);
        xiaomi.setPrice(new BigDecimal("299.99"));
        xiaomi.setStatus(DeviceStatus.LOW_STOCK);
        xiaomi.setAvailable(true);

        repository.saveAll(List.of(iphone, samsung, xiaomi));
    }

    public DeviceResponse saveDevice(DeviceRequest request) {
        Device device = new Device();
        updateDeviceFromRequest(device, request);
        return convertToResponse(repository.save(device));
    }

    public List<DeviceResponse> getAllDevices() {
        return repository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<DeviceResponse> searchDevices(SearchCriteria criteria) {
        List<Device> devices = repository.findDevicesBySpecs(
                criteria.getManufacturers(),
                criteria.getColors(),
                criteria.getStorageCapacities(),
                criteria.getInStockOnly()
        );

        return devices.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private DeviceResponse convertToResponse(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getManufacturer(),
                device.getModel(),
                device.getColorCode(),
                device.getStorageCapacity(),
                device.getPrice(),
                device.getStatus(),
                device.getAuditMetadata()
        );
    }

    public DeviceResponse updateDevice(Long id, DeviceRequest request) {
        Device existingDevice = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Device not found with id: " + id));

        updateDeviceFromRequest(existingDevice, request);
        return convertToResponse(repository.save(existingDevice));
    }

    private void updateDeviceFromRequest(Device device, DeviceRequest request) {
        device.setManufacturer(request.getManufacturer());
        device.setModel(request.getModel());
        device.setColorCode(request.getColorCode());
        device.setStorageCapacity(request.getStorageCapacity());
        device.setPrice(request.getPrice());
        device.setStatus(request.getStatus());
        device.setAvailable(request.isAvailable());
    }
}