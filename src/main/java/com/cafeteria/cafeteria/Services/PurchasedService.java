package com.cafeteria.cafeteria.Services;

import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.Models.User;
import com.cafeteria.cafeteria.Repository.UserRepository;

@Service
public class PurchasedService {
    
    private UserRepository userRepository;
    public PurchasedService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private InsigniaService insigniaService;
    public PurchasedService (InsigniaService insigniaService) {
        this.insigniaService = insigniaService;
    }

    public void purchasedProduct(String cpf) {
        
        User user = userRepository.findByCpf(cpf);
        Integer volumeNow = user.getVolume();
        Integer volumeNew = volumeNow + 1;
        user.setVolume(volumeNew);

        insigniaService.insigniaUpdate(cpf);
    }

}
