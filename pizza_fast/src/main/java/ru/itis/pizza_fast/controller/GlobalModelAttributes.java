package ru.itis.pizza_fast.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import ru.itis.pizza_fast.service.CartService;
import ru.itis.pizza_fast.service.UserService;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {
    private final CartService cartService;
    private final UserService userService;

    @ModelAttribute
    public void addUsernameToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof String && authentication.getPrincipal().equals("anonymousUser"))) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            model.addAttribute("username", username);

            model.addAttribute("quantity", cartService.getTotalQuantity(userService.findByUsername(username)));
        } else {
            model.addAttribute("username", null);
        }
    }
} 