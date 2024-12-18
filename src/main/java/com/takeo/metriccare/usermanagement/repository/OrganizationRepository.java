package com.takeo.metriccare.usermanagement.repository;

import com.takeo.metriccare.usermanagement.model.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
    // Additional custom query methods (if needed) can go here

    // Example: Find an organization by name
    Optional<OrganizationEntity> findByName(String name);
}
