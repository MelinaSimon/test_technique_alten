package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart(userId);
            return cartRepository.save(newCart);
        });
    }

    @Transactional
    public Cart addToCart(Long userId, Long productId, int quantity) {
        if (!productRepository.findById(productId).isPresent()) {
            throw new IllegalArgumentException("Le produit avec l'ID " + productId + " n'existe pas.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Veuillez saisir une quantité positive");
        }

        Cart cart = getCartByUserId(userId);

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(cart, productId, quantity);
            cart.addItem(newItem);
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeFromCart(Long userId, Long productId, int quantity) {
        Cart cart = getCartByUserId(userId);

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() - quantity);
            if (existingItem.get().getQuantity() <= 0) {
                cart.getItems().removeIf(item -> item.getProductId().equals(productId));
            }
        } else {
            throw new IllegalArgumentException("Le produit avec l'ID " + productId + " n'est pas présent dans le panier");
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateQuantity(Long userId, Long productId, int quantity) {
        Cart cart = getCartByUserId(userId);

        cart.getItems().forEach(item -> {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
            }
        });

        return cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.clearCart();
        cartRepository.save(cart);
    }
}
