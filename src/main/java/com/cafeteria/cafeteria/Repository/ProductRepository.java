package com.cafeteria.cafeteria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.cafeteria.Models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
