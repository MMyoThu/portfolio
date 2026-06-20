package com.mta.portfolio.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditDashboardResponse {
    private long totalVisitors;
    private long totalVisits;
    private String mostVisitedPage;
    private long todaysVisits;
}
