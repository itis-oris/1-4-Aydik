package ru.itis.pizza_fast.service;

import ru.itis.pizza_fast.model.User;

public interface UserService {
    User findByUsername(String username);
}