package com.yo.day1.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.dto.auth.AuthResponse;
import com.yo.day1.dto.auth.ChangePasswordRequest;
import com.yo.day1.dto.auth.CurrentUserResponse;
import com.yo.day1.dto.auth.LoginRequest;
import com.yo.day1.dto.auth.RefreshTokenRequest;
import com.yo.day1.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success("Login successful", authService.login(request));
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ApiResponse.success("Token refreshed", authService.refresh(request));
    }

    @PostMapping("/change-password")
    public ApiResponse<Void> changePassword(
        Principal principal,
        @Valid @RequestBody ChangePasswordRequest request
    ) {
        authService.changePassword(principal.getName(), request);
        return ApiResponse.successMessage("Password changed successfully");
    }

    @GetMapping("/me")
    public ApiResponse<CurrentUserResponse> me(Principal principal) {
        return ApiResponse.success(authService.me(principal.getName()));
    }
}
