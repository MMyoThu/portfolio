package com.mta.portfolio.audit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuditRequest {
    @NotBlank
    private String pageName;
    private String sessionId;
    private String visitorIp;
    private String browser;
    private String country;
    private String city;
}
