package com.example.mobileapp.controller;

import com.example.mobileapp.dto.DeviceRequest;
import com.example.mobileapp.service.DeviceService;
import com.example.mobileapp.dto.SearchCriteria;
import com.example.mobileapp.dto.DeviceResponse;
import com.example.mobileapp.model.Device;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/generate-samples")
    public ResponseEntity<String> generateSampleData() {
        deviceService.generateSampleData();
        return ResponseEntity.ok("Sample data generated successfully");
    }

    @PostMapping
    public ResponseEntity<DeviceResponse> saveDevice(@RequestBody DeviceRequest request) {
        return ResponseEntity.ok(deviceService.saveDevice(request));
    }

    @GetMapping("/search")
    public List<DeviceResponse> searchDevices(
            @RequestParam(required = false) List<String> manufacturer,
            @RequestParam(required = false) List<String> color,
            @RequestParam(required = false) List<Integer> storage,
            @RequestParam(required = false, defaultValue = "true") Boolean inStock
    ) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setManufacturers(manufacturer);
        criteria.setColors(color);
        criteria.setStorageCapacities(storage);
        criteria.setInStockOnly(inStock);
        return deviceService.searchDevices(criteria);
    }

    @GetMapping
    public List<DeviceResponse> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponse> updateDevice(
            @PathVariable Long id,
            @RequestBody DeviceRequest request) {
        return ResponseEntity.ok(deviceService.updateDevice(id, request));
    }
}