package com.example.demo.service;

import com.example.demo.model.Wishlist;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WishlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    public WishlistService(WishlistRepository wishlistRepository, ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
    }

    public Wishlist getWishlistByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId).orElseGet(() -> {
            Wishlist newWishlist = new Wishlist(userId);
            return wishlistRepository.save(newWishlist);
        });
    }

    @Transactional
    public Wishlist addToWishlist(Long userId, Long productId) {
        
        if (!productRepository.findById(productId).isPresent()) {
            throw new IllegalArgumentException("Le produit avec l'ID " + productId + " n'existe pas.");
        }
        Wishlist wishlist = getWishlistByUserId(userId);
        wishlist.addProduct(productId);
        return wishlistRepository.save(wishlist);
    }

    @Transactional
    public Wishlist removeFromWishlist(Long userId, Long productId) {
        Wishlist wishlist = getWishlistByUserId(userId);
        wishlist.removeProduct(productId);
        return wishlistRepository.save(wishlist);
    }

    @Transactional
    public void clearWishlist(Long userId) {
        Wishlist wishlist = getWishlistByUserId(userId);
        wishlist.clearWishlist();
        wishlistRepository.save(wishlist);
    }
}