package com.reading.readingisgood.service;

import com.reading.readingisgood.config.security.CustomUserDetailsService;
import com.reading.readingisgood.config.security.JwtTokenProvider;
import com.reading.readingisgood.config.security.UserPrincipal;
import com.reading.readingisgood.model.JwtAuthenticationResponse;
import com.reading.readingisgood.model.user.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public JwtAuthenticationResponse authenticate(LoginRequest loginRequest) {
        Authentication authentication =
                this.authenticationManager.authenticate((Authentication) new UsernamePasswordAuthenticationToken(loginRequest.getMobileNumber(), loginRequest
                        .getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return JwtAuthenticationResponse.builder()
                .tokenType("Bearer")
                .accessToken(this.jwtTokenProvider.generateToken(authentication))
                .build();
    }

    public boolean validate(String token) {
        return this.jwtTokenProvider.validateToken(token);
    }
}