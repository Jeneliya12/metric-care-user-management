package com.takeo.metriccare.usermanagement.controller;


import com.takeo.metriccare.usermanagement.model.dto.AuthResponseDto;
import com.takeo.metriccare.usermanagement.model.dto.LoginDto;
import com.takeo.metriccare.usermanagement.model.dto.request.UserRequest;
import com.takeo.metriccare.usermanagement.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {this.authService = authService;}

    // Build Login REST API
    @PostMapping("/token")
    public ResponseEntity<AuthResponseDto> getToken(@RequestBody LoginDto loginDto) {

        //01 - Receive the token from AuthService
        String token = authService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDto authResponseDto = new AuthResponseDto(token);
        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @PostMapping("/register/super-admin")
    public ResponseEntity registerSuperAdmin(@RequestBody UserRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Register");
    }


}
