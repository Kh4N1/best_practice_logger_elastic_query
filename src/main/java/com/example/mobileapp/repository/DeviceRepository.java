package com.example.mobileapp.repository;

import com.example.mobileapp.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    @Query("SELECT d FROM Device d WHERE " +
            "(:brands IS NULL OR d.manufacturer IN :brands) AND " +
            "(:colors IS NULL OR d.colorCode IN :colors) AND " +
            "(:capacities IS NULL OR d.storageCapacity IN :capacities) AND " +
            "(:inStock IS NULL OR d.available = :inStock)")
    List<Device> findDevicesBySpecs(
            @Param("brands") List<String> brands,
            @Param("colors") List<String> colors,
            @Param("capacities") List<Integer> capacities,
            @Param("inStock") Boolean inStock
    );
}