package com.example.Authentication.services.AuthServ;

import com.example.Authentication.dto.ResetPasswordRequest;
import com.example.Authentication.dto.SignupRequest;
import com.example.Authentication.entity.User;
import com.example.Authentication.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepository userRepository;

    @Override
    public User createUser(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(signupRequest, user);

        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashedPassword);
        User created = userRepository.save(user);
        user.setId(created.getId());

        return user;
    }

    @Override
    public User updatePassword(ResetPasswordRequest request)
    {
        String email = request.getEmail();
        String newPassword = request.getPassword();

        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found with email: " + email);
        }

        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return user;
    }
}