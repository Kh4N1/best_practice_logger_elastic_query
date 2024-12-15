package com.example.mobileapp.model;

import com.example.mobileapp.audit.AuditListener;
import com.example.mobileapp.audit.AuditMetadata;
import com.example.mobileapp.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "devices")
@EntityListeners(AuditListener.class)
@Getter
@Setter
public class Device implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufacturer;
    private String model;
    private String colorCode;
    private Integer storageCapacity;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    @Column(name = "in_stock")
    private boolean available;

    @Embedded
    private AuditMetadata auditMetadata = new AuditMetadata();

    @Override
    public void setAuditMetadata(AuditMetadata auditMetadata) {
        this.auditMetadata = auditMetadata;
    }
}