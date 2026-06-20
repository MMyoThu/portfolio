package com.mta.portfolio.audit.repository;

import com.mta.portfolio.audit.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    long countBySessionIdIsNotNull();
    long countByVisitTimeGreaterThanEqual(LocalDateTime dateTime);

    @Query("SELECT a.pageName FROM AuditLog a GROUP BY a.pageName ORDER BY COUNT(a) DESC")
    List<String> findMostVisitedPages();
}
