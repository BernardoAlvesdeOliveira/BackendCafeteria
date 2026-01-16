package com.cafeteria.cafeteria.DTOs.UserDTO;

import com.cafeteria.cafeteria.Models.User;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String insignia;
    private Integer volume;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.insignia = user.getInsignia();
        this.volume = user.getVolume();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getInsignia() {
        return insignia;
    }

    public Integer getVolume() {
        return volume;
    }
    
}
