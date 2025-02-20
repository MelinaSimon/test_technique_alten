package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long userId,
                                          @RequestParam Long productId,
                                          @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long userId,
                                               @RequestParam Long productId,
                                               @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.removeFromCart(userId, productId, quantity));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Cart> updateQuantity(@PathVariable Long userId,
                                               @RequestParam Long productId,
                                               @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateQuantity(userId, productId, quantity));
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}
