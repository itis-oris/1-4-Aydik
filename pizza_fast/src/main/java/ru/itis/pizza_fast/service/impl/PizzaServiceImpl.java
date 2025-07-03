package ru.itis.pizza_fast.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.pizza_fast.model.Pizza;
import ru.itis.pizza_fast.repository.PizzaRepository;
import ru.itis.pizza_fast.service.PizzaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Override
    public Optional<Pizza> findById(Long id) {
        return pizzaRepository.findById(id);
    }

    @Override
    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public void deleteById(Long id) {
        pizzaRepository.deleteById(id);
    }
} 