package com.cafeteria.cafeteria.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.Models.Product;
import com.cafeteria.cafeteria.Services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public String createProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping("/read")
    public List<Product> readAllproducts() {
        return productService.read();
    }

    @GetMapping("/read/{id}")
    public Optional<Product> readProductById(@PathVariable Long id) {
        return productService.readById(id);
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
    }

}
