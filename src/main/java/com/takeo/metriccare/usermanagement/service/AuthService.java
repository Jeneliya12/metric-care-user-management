package com.takeo.metriccare.usermanagement.service;

import com.takeo.metriccare.usermanagement.dto.LoginDto;
import com.takeo.metriccare.usermanagement.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    void register(RegisterDto registerDto);
}