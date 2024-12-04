package com.takeo.metriccare.usermanagement.service.impl;

import com.takeo.metriccare.usermanagement.config.JwtTokenProvider;
import com.takeo.metriccare.usermanagement.dto.LoginDto;
import com.takeo.metriccare.usermanagement.dto.RegisterDto;
import com.takeo.metriccare.usermanagement.mapper.UserMapper;
import com.takeo.metriccare.usermanagement.repository.UserRepository;
import com.takeo.metriccare.usermanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDto loginDto) {

        // 01 - AuthenticationManager is used to authenticate the user
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDto.username(),
                        loginDto.password()
                );
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 03 - Generate the token based on username and secret key
        // and Return the token to controller
        return jwtTokenProvider.generateToken(authentication);

    }

    @Override
    public void register(RegisterDto registerDto) {
        String plainPassword = registerDto.getPassword();
        String encryptedPassword = passwordEncoder.encode(plainPassword);
        registerDto.setPassword(encryptedPassword);
        userRepository.saveAndFlush(UserMapper.toEntity(registerDto));
    }
}
