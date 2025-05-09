package com.example.demo.Controller;

import com.example.demo.DTO.Request.AuthRequest;
import com.example.demo.DTO.Response.AuthResponse;
import com.example.demo.DTO.Response.ResponseNorm;
import com.example.demo.Service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    final AuthService authService;

    @PostMapping
    public ResponseNorm<AuthResponse> Login(@RequestBody AuthRequest request) {
        return ResponseNorm
                .<AuthResponse>builder()
                .result(authService.authenticate(request))
                .build();
    }
}
