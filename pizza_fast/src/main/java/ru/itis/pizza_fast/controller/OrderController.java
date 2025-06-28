package ru.itis.pizza_fast.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.pizza_fast.model.Order;
import ru.itis.pizza_fast.model.OrderDetails;
import ru.itis.pizza_fast.model.User;
import ru.itis.pizza_fast.service.EmailSenderService;
import ru.itis.pizza_fast.service.OrderService;
import ru.itis.pizza_fast.service.UserService;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final EmailSenderService emailSenderService;


    @PostMapping("/create")
    public String createOrderByCart(@AuthenticationPrincipal UserDetails userDetails,
                                    @RequestParam String address,
                                    @RequestParam String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            return "redirect:/cart?error=email";
        }

        try {
            User user = userService.findByUsername(userDetails.getUsername());
            Order order = orderService.createOrderFromCart(user, address);

            sendOrderEmail(order, email, address);

            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/cart?error=true";
        }
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.findById(id).orElse(null);
    }

    public void sendOrderEmail(Order order, String email, String address) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("----- Чек -----\n");
        for (OrderDetails.Product p : order.getDetails().getProducts()) {
            receipt.append(String.format("%-15s %6.2f x %2d\n", p.getName(), p.getPrice(), p.getQuantity()));
        }
        receipt.append("----------------\n");
        receipt.append(String.format("ИТОГО: %8.2f\n", order.getDetails().getTotalPrice()));
        receipt.append("----------------\n\n");
        receipt.append("Адрес доставки: ").append(address).append("\n");

        boolean answer = emailSenderService.sendEmail(email, "Ваш заказ принят", receipt.toString());
        System.out.println("Email sent: " + answer);
    }
}
