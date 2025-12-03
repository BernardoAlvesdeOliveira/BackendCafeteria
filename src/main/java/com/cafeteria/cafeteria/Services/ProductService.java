package com.cafeteria.cafeteria.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.Models.Product;
import com.cafeteria.cafeteria.Repository.ProductRepository;

@Service
public class ProductService {
    
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String create(Product product) {
        productRepository.save(product);
        return "Produto criado com sucesso!";
    }

    public List<Product> read() {
        return productRepository.findAll();
    }

    public Optional<Product> readById(Long id) {
        return productRepository.findById(id);
    }

    public String update(Long id, Product productNew) {
        productNew.setId(id);
        productRepository.save(productNew);
        return "Produto atualizado com sucesso!";
    }

    public String delete(Long id) {
        productRepository.deleteById(id);
        return "Produto deletado com sucesso!";
    }

}
