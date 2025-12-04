package com.cafeteria.cafeteria.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.Models.User;
import com.cafeteria.cafeteria.Repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String create(User user) {
        userRepository.save(user);
        return "Usuário criado com sucesso!";
    }

    public List<User> read() {
        return userRepository.findAll();
    }

    public Optional<User> readById(Long id) {
        return userRepository.findById(id);
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
