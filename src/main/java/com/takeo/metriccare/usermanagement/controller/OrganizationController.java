package com.takeo.metriccare.usermanagement.controller;

import com.takeo.metriccare.usermanagement.model.dto.request.OrganizationRequest;
import com.takeo.metriccare.usermanagement.model.dto.response.OrganizationResponse;
import com.takeo.metriccare.usermanagement.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(
            OrganizationService organizationService) {this.organizationService = organizationService;}

    // Endpoint to create a new organization (Super Admin only)
    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<String> createOrganization(@RequestBody @Valid OrganizationRequest request,
            Principal principal) {
        organizationService.createOrganization(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Organization created successfully");
    }

    // Endpoint to get all organizations (Super Admin only)
    @GetMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<OrganizationResponse>> getAllOrganizations() {
        List<OrganizationResponse> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations);
    }
}
