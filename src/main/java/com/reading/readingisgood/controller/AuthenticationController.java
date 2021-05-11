package com.reading.readingisgood.controller;

import com.reading.readingisgood.model.JwtAuthenticationResponse;
import com.reading.readingisgood.model.user.LoginRequest;
import com.reading.readingisgood.model.user.RegisterRequest;
import com.reading.readingisgood.service.AuthenticationService;
import com.reading.readingisgood.service.RegistrationService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;

    @ApiOperation("User login endpoint")
    @PostMapping({"/login"})
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(this.authenticationService
                .authenticate(loginRequest));
    }

    @ApiOperation("User register endpoint")
    @PostMapping("/register")
    public ResponseEntity<Long> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(this.registrationService.registerUser(registerRequest));
    }

}