package com.takeo.metriccare.usermanagement.repository;

import com.takeo.metriccare.usermanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
