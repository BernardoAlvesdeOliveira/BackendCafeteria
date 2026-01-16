package com.cafeteria.cafeteria.Services;

import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.Models.User;
import com.cafeteria.cafeteria.Repository.UserRepository;

@Service
public class PurchasedService {
    
    private final UserRepository userRepository;
    private final InsigniaService insigniaService;
    public PurchasedService (InsigniaService insigniaService, UserRepository userRepository) {
        this.insigniaService = insigniaService;
        this.userRepository = userRepository;
    }

    public String purchasedProduct(String cpf) {
        
        User user = userRepository.findByCpf(cpf);
        Integer volumeNow = user.getVolume();
        Integer volumeNew = volumeNow + 1;
        user.setVolume(volumeNew);

        return insigniaService.insigniaUpdate(cpf);
    }

}
