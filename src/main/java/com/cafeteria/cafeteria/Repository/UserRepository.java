package com.cafeteria.cafeteria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.cafeteria.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);
}
