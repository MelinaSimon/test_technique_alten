package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @ElementCollection
    private Set<Long> productIds = new HashSet<>();

    public Wishlist() {}

    public Wishlist(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<Long> getProductIds() {
        return productIds;
    }

    public void addProduct(Long productId) {
        productIds.add(productId);
    }

    public void removeProduct(Long productId) {
        productIds.remove(productId);
    }

    public void clearWishlist() {
        productIds.clear();
    }
}
