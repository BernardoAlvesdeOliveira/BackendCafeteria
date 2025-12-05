package com.cafeteria.cafeteria.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.Services.PurchasedService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/purchased")
public class PurchasedController {
    
    private PurchasedService purchasedService;
    public PurchasedController(PurchasedService purchasedService) {
        this.purchasedService = purchasedService;
    }

    @PostMapping("/bought")
    public String bought(@PathVariable String cpf) {
        return purchasedService.purchasedProduct(cpf);
    }

}
