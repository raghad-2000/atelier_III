package com.authentification.service.authentification.controllers;

import com.authentification.service.authentification.dtos.LogInUserDto;
import com.authentification.service.authentification.dtos.RegisterUserDto;
import com.authentification.service.authentification.entities.LogUser;
import com.authentification.service.authentification.orchestrator.OrchestratorServiceClient;
import com.authentification.service.authentification.responses.LoginResponse;
import com.authentification.service.authentification.services.AuthenticationService;
import com.authentification.service.authentification.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpirationTime;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrchestratorServiceClient orchestratorServiceClient;

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LogInUserDto logInUserDto) {
        LogUser authenticatedUser = authenticationService.authenticate(logInUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/signup")
    public ResponseEntity<LogUser> signup(@RequestBody RegisterUserDto registerUserDto) {

        orchestratorServiceClient.orchestrateSignup(registerUserDto);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/adduser")
    public ResponseEntity<LogUser> adduser(@RequestBody RegisterUserDto registerUserDto) {
        LogUser logUser = authenticationService.addUser(registerUserDto);
        return ResponseEntity.ok(logUser);
    }
}