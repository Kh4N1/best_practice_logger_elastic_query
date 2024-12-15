package com.example.mobileapp.audit;

public interface Auditable {
    AuditMetadata getAuditMetadata();
    void setAuditMetadata(AuditMetadata auditMetadata);
}
