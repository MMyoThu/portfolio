package com.mta.portfolio.audit.controller;

import com.mta.portfolio.audit.dto.AuditDashboardResponse;
import com.mta.portfolio.audit.dto.AuditRequest;
import com.mta.portfolio.audit.entity.AuditLog;
import com.mta.portfolio.audit.service.AuditService;
import com.mta.portfolio.common.response.ApiResponse;
import com.mta.portfolio.common.util.HttpRequestUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @PostMapping("/visit")
    public ResponseEntity<ApiResponse<AuditLog>> recordVisit(@Valid @RequestBody AuditRequest request,
                                                             HttpServletRequest httpRequest) {
        if (!StringUtils.hasText(request.getVisitorIp())) {
            request.setVisitorIp(HttpRequestUtils.resolveClientIp(httpRequest));
        }
        return ResponseEntity.ok(ApiResponse.success(auditService.createVisit(request)));
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponse<AuditDashboardResponse>> getDashboard() {
        return ResponseEntity.ok(ApiResponse.success(auditService.getDashboard()));
    }
}
