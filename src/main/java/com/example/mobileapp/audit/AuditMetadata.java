package com.example.mobileapp.audit;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class AuditMetadata {
    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;
}