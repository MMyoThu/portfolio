package com.mta.portfolio.audit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionId;
    private String pageName;
    private String visitorIp;
    private String browser;
    private String country;
    private String city;

    @Column(name = "visit_time", insertable = false, updatable = false)
    private LocalDateTime visitTime;
}
