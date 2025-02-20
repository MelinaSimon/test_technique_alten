package com.example.demo.controller;

import com.example.demo.model.Wishlist;
import com.example.demo.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Wishlist> getWishlist(@PathVariable Long userId) {
        return ResponseEntity.ok(wishlistService.getWishlistByUserId(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Wishlist> addToWishlist(@PathVariable Long userId,
                                                  @RequestParam Long productId) {
        return ResponseEntity.ok(wishlistService.addToWishlist(userId, productId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Wishlist> removeFromWishlist(@PathVariable Long userId,
                                                       @RequestParam Long productId) {
        return ResponseEntity.ok(wishlistService.removeFromWishlist(userId, productId));
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearWishlist(@PathVariable Long userId) {
        wishlistService.clearWishlist(userId);
        return ResponseEntity.ok().build();
    }
}
