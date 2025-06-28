package ru.itis.pizza_fast.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.pizza_fast.model.CartItem;
import ru.itis.pizza_fast.model.User;
import ru.itis.pizza_fast.service.CartService;
import ru.itis.pizza_fast.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @GetMapping
    public String getCart(@AuthenticationPrincipal UserDetails userDetails,
                          @RequestParam(value = "error", required = false) String error,
                          Model model) {
        User user = userService.findByUsername(userDetails.getUsername());
        List<CartItem> cartItems = cartService.findByUser(user)
                .stream()
                .sorted(Comparator.comparing(item -> item.getPizza().getName(), String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        double totalPrice = cartService.calculateTotalPrice(cartItems);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "cart_page";
    }
} 