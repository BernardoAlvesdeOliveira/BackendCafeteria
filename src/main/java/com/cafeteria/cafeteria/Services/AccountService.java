package com.cafeteria.cafeteria.Services;

import com.cafeteria.cafeteria.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.Repository.UserRepository;

import java.util.Optional;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static UserRepository userRepository;
    private static PasswordEncoder passwordEncoder;
    public AccountService(
            UserRepository useRepository,
            PasswordEncoder passwordEncode
    ) {
        this.userRepository = useRepository;
        this.passwordEncoder = passwordEncode;
    }

    // AUTH SIMPLE OF EMAIL AND PASSWORD
    public boolean validateEmailAndPassword(String email, String senha) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();
        boolean pw = passwordEncoder.matches(senha, user.getPasswordHash());

        System.out.println("Senha recebida: " + senha);
        System.out.println("Hash salvo: " + user.getPasswordHash());
        System.out.println("Matches: " +
                passwordEncoder.matches(senha, user.getPasswordHash())
        );


        return pw;
    }

}