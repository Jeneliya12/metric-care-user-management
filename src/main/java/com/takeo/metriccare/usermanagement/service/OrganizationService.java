package com.takeo.metriccare.usermanagement.service;

import com.takeo.metriccare.usermanagement.model.dto.request.OrganizationRequest;
import com.takeo.metriccare.usermanagement.model.dto.response.OrganizationResponse;
import com.takeo.metriccare.usermanagement.model.entity.OrganizationEntity;
import com.takeo.metriccare.usermanagement.repository.OrganizationRepository;
import com.takeo.metriccare.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;

    public OrganizationService(
            OrganizationRepository organizationRepository,
            UserRepository userRepository) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
    }

    public void createOrganization(OrganizationRequest request, String creator) {
        OrganizationEntity organization = new OrganizationEntity();
        organization.setName(request.getName());
        organization.setDescription(request.getDescription());
        organization.setCreatedBy(userRepository.findByUsername(creator).orElseThrow());
        organizationRepository.save(organization);
    }

    public List<OrganizationResponse> getAllOrganizations() {
        return organizationRepository.findAll().stream()
                .map(org -> new OrganizationResponse(org.getId(), org.getName(), org.getDescription()))
                .toList();
    }
}
