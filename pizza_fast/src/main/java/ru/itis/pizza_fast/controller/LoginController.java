package ru.itis.pizza_fast.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping()
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model, HttpServletRequest request) {
        if (error != null) {
            model.addAttribute("error", true);
        }
        return "login_page";
    }
}