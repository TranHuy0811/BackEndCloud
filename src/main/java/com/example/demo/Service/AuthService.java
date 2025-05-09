package com.example.demo.Service;

import com.example.demo.Component.JWTHandler;
import com.example.demo.DB.User;
import com.example.demo.DTO.Request.AuthRequest;
import com.example.demo.DTO.Response.AuthResponse;
import com.example.demo.Exception.AppException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.Repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class AuthService {
    final JWTHandler jwtHandler;
    final UserRepo userRepo;
    final PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean isAuthenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!isAuthenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

        return AuthResponse
                .builder()
                .token(jwtHandler.generateToken(user))
                .build();
    }
}
