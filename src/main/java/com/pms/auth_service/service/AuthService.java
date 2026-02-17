package com.pms.auth_service.service;

import com.pms.auth_service.dtos.LoginRequestDTO;

import java.util.Optional;

public interface AuthService {
    Optional<String> authenticate(LoginRequestDTO requestDTO);
}
