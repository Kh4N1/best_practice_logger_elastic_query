package com.example.mobileapp.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AuditListener {

    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof Auditable) {
            AuditMetadata metadata = new AuditMetadata();
            metadata.setCreatedAt(LocalDateTime.now());
            metadata.setCreatedBy(getCurrentRequestId());
            metadata.setLastModifiedAt(LocalDateTime.now());
            metadata.setLastModifiedBy(getCurrentRequestId());
            ((Auditable) entity).setAuditMetadata(metadata);
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity instanceof Auditable) {
            AuditMetadata metadata = ((Auditable) entity).getAuditMetadata();
            metadata.setLastModifiedAt(LocalDateTime.now());
            metadata.setLastModifiedBy(getCurrentRequestId());
        }
    }

    private String getCurrentRequestId() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .map(HttpServletRequest::getRequestId)
                .orElse("system");
    }
}