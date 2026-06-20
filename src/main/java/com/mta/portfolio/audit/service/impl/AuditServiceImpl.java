package com.mta.portfolio.audit.service.impl;

import com.mta.portfolio.audit.dto.AuditDashboardResponse;
import com.mta.portfolio.audit.dto.AuditRequest;
import com.mta.portfolio.audit.entity.AuditLog;
import com.mta.portfolio.audit.repository.AuditLogRepository;
import com.mta.portfolio.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public AuditLog createVisit(AuditRequest request) {
        AuditLog auditLog = new AuditLog();
        auditLog.setSessionId(request.getSessionId());
        auditLog.setPageName(request.getPageName());
        auditLog.setVisitorIp(request.getVisitorIp());
        auditLog.setBrowser(request.getBrowser());
        auditLog.setCountry(request.getCountry());
        auditLog.setCity(request.getCity());
        return auditLogRepository.save(auditLog);
    }

    @Override
    public AuditDashboardResponse getDashboard() {
        long totalVisits = auditLogRepository.count();
        long totalVisitors = auditLogRepository.countBySessionIdIsNotNull();
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        long todaysVisits = auditLogRepository.countByVisitTimeGreaterThanEqual(startOfDay);
        List<String> pages = auditLogRepository.findMostVisitedPages();
        String mostVisitedPage = pages.isEmpty() ? null : pages.get(0);
        return new AuditDashboardResponse(totalVisitors, totalVisits, mostVisitedPage, todaysVisits);
    }
}
