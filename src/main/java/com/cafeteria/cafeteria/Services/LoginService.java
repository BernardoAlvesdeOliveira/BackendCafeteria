package com.cafeteria.cafeteria.Services;

import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.Repository.UserRepository;

@Service
public class LoginService {

    private final UserRepository userRepository;
    public class LoginService(UserRepository useRepository) {
        this.userRepository = useRepository;
    }
}