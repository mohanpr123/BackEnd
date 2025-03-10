package com.example.Authentication.services.AuthServ;

import com.example.Authentication.dto.ResetPasswordRequest;
import com.example.Authentication.dto.SignupRequest;
import com.example.Authentication.entity.User;

public interface AuthService  {

    User createUser(SignupRequest signupRequest);
    User updatePassword(ResetPasswordRequest request);
}
