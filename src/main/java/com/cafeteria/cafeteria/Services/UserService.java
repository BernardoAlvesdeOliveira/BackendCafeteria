package com.cafeteria.cafeteria.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.DTOs.UserDTO.UserRequestDTO;
import com.cafeteria.cafeteria.DTOs.UserDTO.UserResponseDTO;
import com.cafeteria.cafeteria.Models.User;
import com.cafeteria.cafeteria.Repository.UserRepository;
import com.cafeteria.cafeteria.Configurations.SecurityConfig;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    
    public UserService(
        UserRepository userRepository,
        SecurityConfig securityConfig
    ) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public User create(UserRequestDTO dto) {
        
        User user = new User();
        user.setCpf(dto.getCpf());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(securityConfig.passwordEncoder().encode(dto.getPassword()));
        user.setVolume(0);
        user.setInsignia("Bronze");

        return userRepository.save(user);
    }

    public List<User> read() {
        return userRepository.findAll();
    }

    public User readById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public String update(Long id, User userNew) {
        userNew.setId(id);
        userRepository.save(userNew);
        return "Usuário atualizado com sucesso!";
    }

    public String delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        String name = user.get().getName();
        userRepository.deleteById(id);
        return "Usuário " + name + " deletado com sucesso!";
    }

}
