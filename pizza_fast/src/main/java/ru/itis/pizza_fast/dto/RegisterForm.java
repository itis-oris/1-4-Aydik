package ru.itis.pizza_fast.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterForm {

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 4, max = 50, message = "Имя пользователя должно быть от 4 до 50 символов")
    private String username;

    @NotBlank(message = "Телефон не может быть пустым")
    @Pattern(
        regexp = "^\\+7\\s?\\(9\\d{2}\\)\\s?\\d{3}-\\d{2}-\\d{2}$",
        message = "Телефон должен быть в формате +7 (9XX) XXX-XX-XX"
    )
    private String phone;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, message = "Минимальная длина пароля — 8 символов")
    private String password;

    @NotBlank(message = "Пароль не может быть пустым")
    private String passwordCopy;

    public boolean isPasswordsMatching() {
        return password != null && password.equals(passwordCopy);
    }
}
