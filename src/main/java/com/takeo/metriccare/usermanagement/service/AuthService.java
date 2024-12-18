package com.takeo.metriccare.usermanagement.service;

import com.takeo.metriccare.usermanagement.config.JwtTokenProvider;
import com.takeo.metriccare.usermanagement.mapper.UserMapper;
import com.takeo.metriccare.usermanagement.model.dto.LoginDto;
import com.takeo.metriccare.usermanagement.model.dto.request.UserRequest;
import com.takeo.metriccare.usermanagement.model.enums.RoleEnum;
import com.takeo.metriccare.usermanagement.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


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

    public void register(UserRequest request) {
        String plainPassword = request.getPassword();
        String encryptedPassword = passwordEncoder.encode(plainPassword);

        request.setPassword(encryptedPassword);
        request.setRole(RoleEnum.SUPER_ADMIN.getName());
        request.setOrganizationId(-1L);

        userRepository.saveAndFlush(UserMapper.toEntity(request));
    }
}
