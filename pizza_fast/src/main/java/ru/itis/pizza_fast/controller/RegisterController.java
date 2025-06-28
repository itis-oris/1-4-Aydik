package ru.itis.pizza_fast.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.pizza_fast.dto.RegisterForm;
import ru.itis.pizza_fast.security.details.UserDetailsServiceImpl;
import ru.itis.pizza_fast.service.RegisterService;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping()
    public String getRegisterPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register_page";
    }

    @PostMapping()
    public String register(@Valid @ModelAttribute RegisterForm registerForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldErrors().getFirst().getDefaultMessage());
            return "register_page";
        }

        if (!registerForm.isPasswordsMatching()) {
            model.addAttribute("error", "Пароли не совпадают");
            return "register_page";
        }

        if (registerService.usernameExists(registerForm.getUsername())) {
            model.addAttribute("error", "Имя пользователя уже занято");
            return "register_page";
        }

        String cleanedPhone = registerForm.getPhone().replaceAll("[^0-9]", "");
        if (registerService.phoneExists(cleanedPhone)) {
            model.addAttribute("error", "Телефон уже используется");
            return "register_page";
        }

        registerService.register(registerForm);

        return "redirect:/login";
    }

}
