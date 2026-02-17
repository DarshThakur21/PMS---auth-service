package com.pms.auth_service.service.impl;


import com.pms.auth_service.model.User;
import com.pms.auth_service.repository.UserRepository;
import com.pms.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
