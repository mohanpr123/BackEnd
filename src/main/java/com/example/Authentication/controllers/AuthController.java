package com.example.Authentication.controllers;

import com.example.Authentication.dto.*;
import com.example.Authentication.entity.User;
import com.example.Authentication.repository.UserRepository;
import com.example.Authentication.services.AuthServ.AuthServiceImpl;
import com.example.Authentication.services.AuthServ.UserServiceImpl;
import com.example.Authentication.utility.JwtUtil;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    @Autowired private UserServiceImpl userService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private AuthServiceImpl authService;
    @Autowired private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest)
    {
        User createdUser = authService.createUser(signupRequest);
        if(createdUser!=null)
        {
            return ResponseEntity.status(200).body(createdUser);
        }
        else
        {
            return ResponseEntity.status(400).body("User Already Exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
            throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400)
                    .body(e.getMessage());
        }

        final UserDetails user = userService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken((user.getUsername()));

        final String username =userService.Username(loginRequest.getEmail());
        return ResponseEntity.ok(new LoginResponse(jwt,username));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            if(!userRepository.existsByEmail(request.getEmail()))
            {
                return ResponseEntity.status(404).body("User not found with email: ");
            }
            authService.updatePassword(request);
            return ResponseEntity.ok(new ResetResponse("Password reset successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to reset password: " + e.getMessage());
        }
    }
}
