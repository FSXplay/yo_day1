package com.yo.day1.service;

import com.yo.day1.domain.entity.User;
import com.yo.day1.dto.auth.AuthResponse;
import com.yo.day1.dto.auth.ChangePasswordRequest;
import com.yo.day1.dto.auth.CurrentUserResponse;
import com.yo.day1.dto.auth.LoginRequest;
import com.yo.day1.dto.auth.RefreshTokenRequest;

public interface AuthService {
    public AuthResponse login(LoginRequest request);

    public AuthResponse refresh(RefreshTokenRequest request);

    public void changePassword(String username, ChangePasswordRequest request);

    public CurrentUserResponse me(String username);

    public User findActiveUserByUsername(String username);
}
