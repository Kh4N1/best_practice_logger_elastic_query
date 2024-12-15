package com.example.mobileapp.audit;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuditLogger {

    public void logCreation(Auditable entity) {
        AuditMetadata metadata = entity.getAuditMetadata();
        log.info("Entity created - By: {}, At: {}",
                metadata.getCreatedBy(),
                metadata.getCreatedAt());
    }

    public void logModification(Auditable entity) {
        AuditMetadata metadata = entity.getAuditMetadata();
        log.info("Entity modified - By: {}, At: {}",
                metadata.getLastModifiedBy(),
                metadata.getLastModifiedAt());
    }
}