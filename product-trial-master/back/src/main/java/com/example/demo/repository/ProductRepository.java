package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Trouver un produit par son code
    Product findByCode(String code);
    Optional<Product> findById(Long id);

    // Récupérer tous les produits d'une catégorie
    List<Product> findByCategory(String category);

    // Récupérer les produits dont le stock est inférieur à une valeur donnée
    List<Product> findByQuantityLessThan(int quantity);
}
