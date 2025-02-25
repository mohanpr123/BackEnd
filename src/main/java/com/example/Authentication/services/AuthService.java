package com.example.Authentication.services;

import com.example.Authentication.dto.ResetPasswordRequest;
import com.example.Authentication.dto.SignupRequest;
import com.example.Authentication.entity.User;

import java.util.Optional;

public interface AuthService  {

    User createUser(SignupRequest signupRequest);
    Optional<User> updatePassword(ResetPasswordRequest request);
}
