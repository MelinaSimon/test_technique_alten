package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Créer un produit (POST)
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Récupérer tous les produits (GET)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Récupérer un produit spécifique par ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mettre à jour un produit s'il existe (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Product> existingProductOpt = productService.getProductById(id);
        
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();

            if (product.getName() != null) {
                existingProduct.setName(product.getName());
            }
            if (product.getDescription() != null) {
                existingProduct.setDescription(product.getDescription());
            }
            if (product.getImage() != null) {
                existingProduct.setImage(product.getImage());
            }
            if (product.getCategory() != null) {
                existingProduct.setCategory(product.getCategory());
            }
            if (product.getPrice() != null) {
                existingProduct.setPrice(product.getPrice());
            }
            if (product.getQuantity() != null) {
                existingProduct.setQuantity(product.getQuantity());
            }
            if (product.getInternalReference() != null) {
                existingProduct.setInternalReference(product.getInternalReference());
            }
            if (product.getShellId() != null) {
                existingProduct.setShellId(product.getShellId());
            }
            if (product.getInventoryStatus() != null) {
                existingProduct.setInventoryStatus(product.getInventoryStatus());
            }
            if (product.getRating() != null) {
                existingProduct.setRating(product.getRating());
            }

            existingProduct.setUpdatedAt(System.currentTimeMillis());

            Product updatedProduct = productService.updateProduct(existingProduct);
            
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Supprimer un produit (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}