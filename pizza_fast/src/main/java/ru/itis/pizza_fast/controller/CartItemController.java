package ru.itis.pizza_fast.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.pizza_fast.model.Pizza;
import ru.itis.pizza_fast.model.User;
import ru.itis.pizza_fast.service.CartService;
import ru.itis.pizza_fast.service.PizzaService;
import ru.itis.pizza_fast.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartItemController {
    private final CartService cartService;
    private final PizzaService pizzaService;
    private final UserService userService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addToCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long pizzaId) {
        try {
            User user = userService.findByUsername(userDetails.getUsername());
            Optional<Pizza> pizzaOpt = pizzaService.findById(pizzaId);
            cartService.addToCart(user, pizzaOpt);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reduce")
    @ResponseBody
    public ResponseEntity<?> reduceQuantity(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long pizzaId) {
        try {
            User user = userService.findByUsername(userDetails.getUsername());
            Optional<Pizza> pizzaOpt = pizzaService.findById(pizzaId);
            cartService.reduceQuantity(user, pizzaOpt);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> deleteFromCart(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long pizzaId) {
        try {
            User user = userService.findByUsername(userDetails.getUsername());
            Optional<Pizza> pizzaOpt = pizzaService.findById(pizzaId);
            cartService.removeFromCart(user, pizzaOpt);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/clear")
    @ResponseBody
    public ResponseEntity<?> clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            User user = userService.findByUsername(userDetails.getUsername());
            cartService.clearCart(user);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 