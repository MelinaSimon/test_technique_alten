package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Créer un produit
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Récupérer tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Récupérer un produit par son ID
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    // Mettre à jour un produit
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Supprimer un produit
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
