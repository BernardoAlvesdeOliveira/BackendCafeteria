package com.cafeteria.cafeteria.Controllers;

import com.cafeteria.cafeteria.DTOs.LoginDTO.LoginRequestDTO;
import com.cafeteria.cafeteria.Services.AccountService;
import com.cafeteria.cafeteria.Services.JwtService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.cafeteria.cafeteria.DTOs.UserDTO.UserRequestDTO;
import com.cafeteria.cafeteria.DTOs.UserDTO.UserResponseDTO;
import com.cafeteria.cafeteria.Models.User;
import com.cafeteria.cafeteria.Services.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://100.72.47.104:4200")
public class UserController {

    private final JwtService jwtService;

    private static UserService userService;
    private static AccountService accountService;
    public UserController(
            JwtService jwtService,
            UserService userService,
            AccountService accountService
    ) {
        this.jwtService = jwtService;
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
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        System.out.println("Email: >" + request.getEmail() + "<");
        System.out.println("Senha: >" + request.getSenha() + "<");

        boolean validation = accountService.validateEmailAndPassword(request.getEmail(), request.getSenha());

        if (!validation) {
            ResponseEntity<?> r = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            System.out.println("RESPOSTA DE ERRO: " + r);
            return r;
        }

        String token = jwtService.generateToken(request.getEmail());
        System.out.println("TOKEN: " + token);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
