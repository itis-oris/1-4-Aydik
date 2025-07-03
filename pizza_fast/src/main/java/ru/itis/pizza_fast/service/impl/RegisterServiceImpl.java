package ru.itis.pizza_fast.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.pizza_fast.dto.RegisterForm;
import ru.itis.pizza_fast.model.User;
import ru.itis.pizza_fast.model.UserRole;
import ru.itis.pizza_fast.repository.UserRepository;
import ru.itis.pizza_fast.service.RegisterService;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterForm form) {
        String cleanedPhone = form.getPhone().replaceAll("[^0-9]", "");

        User user = User.builder()
                .username(form.getUsername())
                .phone(cleanedPhone)
                .password(passwordEncoder.encode(form.getPassword()))
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean phoneExists(String phone) {
        return userRepository.existsByPhone(phone);
    }
}
