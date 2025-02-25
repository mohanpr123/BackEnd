package com.example.Authentication.controllers;

import com.example.Authentication.dto.ResetPasswordRequest;
import com.example.Authentication.dto.ResetResponse;
import com.example.Authentication.entity.User;
import com.example.Authentication.repository.UserRepository;
import com.example.Authentication.services.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/reset-password")
public class ResetPasswordController {

    private final UserRepository userRepository;
    private final  AuthServiceImpl authService;
    @Autowired

    public ResetPasswordController(AuthServiceImpl authService,UserRepository userRepository) {
        this.userRepository=userRepository;
        this.authService=authService;
    }

    @PostMapping
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request)
    {
        try{
            String email = request.getEmail();
            if (email == null || email.isEmpty()) {
                return ResponseEntity.status(400).body("Email is required");
            }

            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (!optionalUser.isPresent()) {
                return ResponseEntity.status(404).body("User not found with email: " + email);
            }

                    authService.updatePassword(request);
                    String msg="Reset Success";
                    return  ResponseEntity.ok(new ResetResponse(msg));

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
