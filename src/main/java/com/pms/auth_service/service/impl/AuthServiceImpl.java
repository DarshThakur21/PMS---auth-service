package com.pms.auth_service.service.impl;

import com.pms.auth_service.dtos.LoginRequestDTO;
import com.pms.auth_service.model.User;
import com.pms.auth_service.repository.UserRepository;
import com.pms.auth_service.service.AuthService;
import com.pms.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Optional<String> authenticate(LoginRequestDTO requestDTO) {
        Optional<String> token = userService.findByEmail(requestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(requestDTO.getPassword(),u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(),u.getPassword()));
        return token;

    }
}
