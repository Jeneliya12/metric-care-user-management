package com.takeo.metriccare.usermanagement.service;

import com.takeo.metriccare.usermanagement.model.dto.request.UserRequest;
import com.takeo.metriccare.usermanagement.model.dto.response.UserResponse;
import com.takeo.metriccare.usermanagement.model.entity.OrganizationEntity;
import com.takeo.metriccare.usermanagement.model.entity.RoleEntity;
import com.takeo.metriccare.usermanagement.model.entity.UserEntity;
import com.takeo.metriccare.usermanagement.model.enums.RoleEnum;
import com.takeo.metriccare.usermanagement.repository.OrganizationRepository;
import com.takeo.metriccare.usermanagement.repository.RoleRepository;
import com.takeo.metriccare.usermanagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, OrganizationRepository organizationRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(Long organizationId, UserRequest request) {
        OrganizationEntity organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        RoleEntity role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setOrganization(organization);
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

    public List<UserResponse> getUsersByOrganization(Long organizationId) {
        OrganizationEntity organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        return organization.getUsers().stream()
                .map(user -> {
                    Set<RoleEntity> roleEntities = user.getRoles();
                    Set<RoleEnum> roles = roleEntities.stream()
                            .map(roleEntity -> RoleEnum.valueOf(
                                    roleEntity.getName().toUpperCase()
                            )).collect(Collectors.toUnmodifiableSet());
                    return
                            new UserResponse(user.getId(), user.getUsername(), user.getEmail(), roles);
                })
                .toList();
    }
}
