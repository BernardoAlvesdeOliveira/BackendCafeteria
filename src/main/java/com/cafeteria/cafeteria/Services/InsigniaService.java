package com.cafeteria.cafeteria.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.Models.User;
import com.cafeteria.cafeteria.Repository.UserRepository;

@Service
public class InsigniaService {

    private final List<String> insignias = Arrays.asList("Bronze", "Ouro", "Diamante");
    
    private UserRepository userRepository;
    public InsigniaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void insigniaUpdate(String cpf) {
        
        User user = userRepository.findByCpf(cpf);
        String insigniaNow = user.getInsignia();
        Integer userVolume = user.getVolume();

        if (insigniaNow != null && userVolume == 10) {
            for (String insignia : insignias) {

                if (insigniaNow == "Diamante") {
                    user.setInsignia(null);
                    user.setVolume(0);
                    // ADICIONAR EVENTO PARA INFORMAR DA ATUALIZAÇÃO NAS COLUNAS ACIMA
                }

                if (insigniaNow == insignia) {
                    Integer insigniaPosition = insignias.indexOf(insigniaNow);
                    String insigniaNext = insignias.get(insigniaPosition + 1);
                    user.setInsignia(insigniaNext);
                    user.setVolume(0);
                    // ADICIONAR EVENTO PARA INFORMAR DA ATUALIZAÇÃO NAS COLUNAS ACIMA
                }
            }
        }
    }

}
