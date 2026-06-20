package com.mta.portfolio.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardResponse {
    private long totalVisitors;
    private long totalProjects;
    private long totalMessages;
    private long totalGamesPlayed;
}
