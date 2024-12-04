package com.takeo.metriccare.usermanagement.mapper;

import com.takeo.metriccare.usermanagement.dto.RegisterDto;
import com.takeo.metriccare.usermanagement.model.Role;
import com.takeo.metriccare.usermanagement.model.User;

import java.util.Set;

public interface UserMapper {

    static User toEntity(RegisterDto dto) {
        User user = new User();
        Role role = new Role();
        role.setName(dto.getRole());

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoles(Set.of(role));

        return user;
    }
}
