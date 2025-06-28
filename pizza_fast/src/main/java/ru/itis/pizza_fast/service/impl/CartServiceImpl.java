package ru.itis.pizza_fast.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.pizza_fast.model.CartItem;
import ru.itis.pizza_fast.model.Pizza;
import ru.itis.pizza_fast.model.User;
import ru.itis.pizza_fast.repository.CartItemRepository;
import ru.itis.pizza_fast.service.CartService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> findByUser(User user) {
        return cartItemRepository.findByUser(user);
    }

    @Override
    @Transactional
    public void addToCart(User user, Optional<Pizza> pizzaOpt) {
        if (pizzaOpt.isEmpty()) {
            throw new RuntimeException("Pizza not found");
        }
        Pizza pizza = pizzaOpt.get();
        List<CartItem> items = cartItemRepository.findByUser(user);
        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getPizza().getId().equals(pizza.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = CartItem.builder()
                    .user(user)
                    .pizza(pizza)
                    .quantity(1)
                    .build();
            cartItemRepository.save(newItem);
        }
    }

    @Override
    @Transactional
    public void reduceQuantity(User user, Optional<Pizza> pizzaOpt) {
        if (pizzaOpt.isEmpty()) {
            throw new RuntimeException("Pizza not found");
        }
        Pizza pizza = pizzaOpt.get();
        List<CartItem> items = cartItemRepository.findByUser(user);
        items.stream()
                .filter(item -> item.getPizza().getId().equals(pizza.getId()))
                .findFirst()
                .ifPresent(item -> {
                    if (item.getQuantity() > 1) {
                        item.setQuantity(item.getQuantity() - 1);
                        cartItemRepository.save(item);
                    } else {
                        cartItemRepository.delete(item);
                    }
                });
    }

    @Override
    @Transactional
    public void removeFromCart(User user, Optional<Pizza> pizzaOpt) {
        if (pizzaOpt.isEmpty()) {
            throw new RuntimeException("Pizza not found");
        }
        Pizza pizza = pizzaOpt.get();
        List<CartItem> items = cartItemRepository.findByUser(user);
        items.stream()
                .filter(item -> item.getPizza().getId().equals(pizza.getId()))
                .findFirst()
                .ifPresent(cartItemRepository::delete);
    }

    @Override
    @Transactional
    public void clearCart(User user) {
        List<CartItem> items = cartItemRepository.findByUser(user);
        cartItemRepository.deleteAll(items);
    }

    @Override
    public double calculateTotalPrice(List<CartItem> cartItems) {
        return cartItems.stream()
                .mapToDouble(item -> item.getPizza().getPrice() * item.getQuantity())
                .sum();
    }

    @Override
    public int getTotalQuantity(User user) {
        return cartItemRepository.getTotalQuantityByUser(user);
    }
} 