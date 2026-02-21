package com.cafeteria.cafeteria.DTOs.LoginDTO;

public class LoginRequestDTO {
    private String email;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setPassword(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
