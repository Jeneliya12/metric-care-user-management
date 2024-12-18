package com.takeo.metriccare.usermanagement.controller;

import com.takeo.metriccare.usermanagement.model.dto.request.UserRequest;
import com.takeo.metriccare.usermanagement.model.dto.response.UserResponse;
import com.takeo.metriccare.usermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    // Endpoint to create a new user under an organization
    @PostMapping("/{organizationId}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ORGANIZATION_ADMIN')")
    public ResponseEntity<String> createUser(
            @PathVariable Long organizationId,
            @RequestBody @Valid UserRequest request
    ) {
        userService.createUser(organizationId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    // Endpoint to get all users under an organization
    @GetMapping("/{organizationId}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ORGANIZATION_ADMIN')")
    public ResponseEntity<List<UserResponse>> getUsersByOrganization(@PathVariable Long organizationId) {
        List<UserResponse> users = userService.getUsersByOrganization(organizationId);
        return ResponseEntity.ok(users);
    }
}
