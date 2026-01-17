package com.cafeteria.cafeteria.Controllers;

import com.cafeteria.cafeteria.DTOs.LoginDTO.LoginRequestDTO;
import com.cafeteria.cafeteria.Services.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.DTOs.UserDTO.UserRequestDTO;
import com.cafeteria.cafeteria.DTOs.UserDTO.UserResponseDTO;
import com.cafeteria.cafeteria.Models.User;
import com.cafeteria.cafeteria.Services.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/user")
public class UserController {
    
    // UserService
    private static UserService userService;
    private static AccountService accountService;
    public UserController(
            UserService userService,
            AccountService accountService
    ) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public boolean createUser(@RequestBody @Valid UserRequestDTO dto) {
        boolean user = userService.create(dto);
        return user;
    }

    @GetMapping("/read")
    public ResponseEntity<List<UserResponseDTO>> readAllUsers() {
        List<User> users = userService.read();
        List<UserResponseDTO> response = users.stream()
            .map(UserResponseDTO::new)
            .toList();  
        return ResponseEntity.ok(response);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserResponseDTO> readUserById(@PathVariable Long id) {
        User user = userService.readById(id);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }

    // VALIDATION ACCOUNT
    @PostMapping("/login")
    public boolean loginAccount(@RequestBody @Valid LoginRequestDTO request) {
        return accountService.validateEmailAndPassword(request.getEmail(), request.getPassword());
    }
}
