package ru.itis.pizza_fast.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.pizza_fast.model.CartItem;
import ru.itis.pizza_fast.model.Pizza;
import ru.itis.pizza_fast.model.ProductWithQuantity;
import ru.itis.pizza_fast.model.User;
import ru.itis.pizza_fast.service.CartService;
import ru.itis.pizza_fast.service.PizzaService;
import ru.itis.pizza_fast.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainPageController {
    private final PizzaService pizzaService;
    private final UserService userService;
    private final CartService cartService;

    private record CartData(Map<Long, Integer> pizzaQuantities, int totalQuantity) {
    }

    private CartData getCartData(String username) {
        if (username == null) {
            return new CartData(Map.of(), 0);
        }

        try {
            User user = userService.findByUsername(username);
            List<CartItem> userCartItems = cartService.findByUser(user);
            Map<Long, Integer> quantities = userCartItems.stream()
                    .collect(Collectors.toMap(
                            item -> item.getPizza().getId(),
                            CartItem::getQuantity
                    ));
            int total = userCartItems.stream()
                    .mapToInt(CartItem::getQuantity)
                    .sum();
            return new CartData(quantities, total);
        } catch (RuntimeException e) {
            return new CartData(Map.of(), 0);
        }
    }

    @GetMapping()
    public String getMainPage(Model model) {
        List<Pizza> pizzas = pizzaService.findAll();
        String username = (String) model.asMap().get("username");
        CartData cartData = getCartData(username);

        List<ProductWithQuantity> products = pizzas.stream()
                .map(pizza -> new ProductWithQuantity(
                        pizza,
                        cartData.pizzaQuantities().getOrDefault(pizza.getId(), 0)
                ))
                .collect(Collectors.toList());

        model.addAttribute("products", products);

        return "main_page";
    }
}
