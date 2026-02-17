package com.pms.auth_service.service;

import com.pms.auth_service.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}
