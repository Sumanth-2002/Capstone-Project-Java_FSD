package com.ust.users_service.controller;


import com.ust.users_service.dto.AuthenticationRequest;
import com.ust.users_service.dto.AuthenticationResponse;
import com.ust.users_service.model.UserInfo;

import com.ust.users_service.repository.UserRepository;
import com.ust.users_service.service.CustomUserDetailsService;
import com.ust.users_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        // Fetch user details from the database
        UserInfo userInfo = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new Exception("User not found with email: " + authenticationRequest.getEmail()));

        // Generate JWT with role, region, or storeId based on role
        final String jwt = jwtUtil.generateToken(
                userInfo.getEmail(),
                userInfo.getRoles().name(),
                userInfo.getRegion(),
                userInfo.getStoreId()
        );

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}