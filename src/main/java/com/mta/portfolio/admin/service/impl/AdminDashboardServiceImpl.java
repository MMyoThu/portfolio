package com.mta.portfolio.admin.service.impl;

import com.mta.portfolio.admin.dto.AdminDashboardResponse;
import com.mta.portfolio.admin.service.AdminDashboardService;
import com.mta.portfolio.audit.repository.AuditLogRepository;
import com.mta.portfolio.contact.repository.ContactMessageRepository;
import com.mta.portfolio.game.repository.GameScoreRepository;
import com.mta.portfolio.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final AuditLogRepository auditLogRepository;
    private final ProjectRepository projectRepository;
    private final ContactMessageRepository contactMessageRepository;
    private final GameScoreRepository gameScoreRepository;

    @Override
    public AdminDashboardResponse getDashboard() {
        return new AdminDashboardResponse(
                auditLogRepository.countBySessionIdIsNotNull(),
                projectRepository.count(),
                contactMessageRepository.count(),
                gameScoreRepository.count()
        );
    }
}
