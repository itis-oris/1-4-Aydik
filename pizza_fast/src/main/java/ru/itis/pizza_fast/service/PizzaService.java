package ru.itis.pizza_fast.service;

import ru.itis.pizza_fast.model.Pizza;
import java.util.List;
import java.util.Optional;

public interface PizzaService {
    List<Pizza> findAll();
    
    Optional<Pizza> findById(Long id);
    
    Pizza save(Pizza pizza);
    
    void deleteById(Long id);
} 