package ru.itis.pizza_fast.service;

import ru.itis.pizza_fast.model.CartItem;
import ru.itis.pizza_fast.model.Pizza;
import ru.itis.pizza_fast.model.User;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<CartItem> findByUser(User user);
    
    void addToCart(User user, Optional<Pizza> pizzaOpt);
    
    void reduceQuantity(User user, Optional<Pizza> pizzaOpt);
    
    void removeFromCart(User user, Optional<Pizza> pizzaOpt);
    
    void clearCart(User user);
    
    double calculateTotalPrice(List<CartItem> cartItems);

    int getTotalQuantity(User user);
}