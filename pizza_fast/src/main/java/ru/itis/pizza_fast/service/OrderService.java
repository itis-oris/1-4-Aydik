package ru.itis.pizza_fast.service;

import ru.itis.pizza_fast.model.Order;
import ru.itis.pizza_fast.model.User;

import java.util.Optional;

public interface OrderService {
    Optional<Order> findById(Long id);
    Order createOrderFromCart(User user, String addressStr);
}
