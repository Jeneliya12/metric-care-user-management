package com.takeo.metriccare.usermanagement.model.dto.response;


import com.takeo.metriccare.usermanagement.model.enums.RoleEnum;

import java.util.Set;

public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private Set<RoleEnum> roles;

    public UserResponse(Long id, String username, String email, Set<RoleEnum> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }
}
