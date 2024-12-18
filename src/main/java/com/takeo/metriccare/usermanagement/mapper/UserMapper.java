package com.takeo.metriccare.usermanagement.mapper;

import com.takeo.metriccare.usermanagement.model.dto.request.UserRequest;
import com.takeo.metriccare.usermanagement.model.entity.RoleEntity;
import com.takeo.metriccare.usermanagement.model.entity.UserEntity;

import java.util.Set;

public interface UserMapper {

    static UserEntity toEntity(UserRequest dto) {

        RoleEntity role = new RoleEntity();
        role.setName(dto.getRole());

        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRoles(Set.of(role));
        return user;
    }
}
