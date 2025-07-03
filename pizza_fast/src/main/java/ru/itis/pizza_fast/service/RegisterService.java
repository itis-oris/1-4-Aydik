package ru.itis.pizza_fast.service;

import ru.itis.pizza_fast.dto.RegisterForm;


public interface RegisterService {
    void register(RegisterForm userForm);
    boolean usernameExists(String username);
    boolean phoneExists(String phone);
}