package com.mta.portfolio.audit.service;

import com.mta.portfolio.audit.dto.AuditDashboardResponse;
import com.mta.portfolio.audit.dto.AuditRequest;
import com.mta.portfolio.audit.entity.AuditLog;

public interface AuditService {

    AuditLog createVisit(AuditRequest request);

    AuditDashboardResponse getDashboard();
}
