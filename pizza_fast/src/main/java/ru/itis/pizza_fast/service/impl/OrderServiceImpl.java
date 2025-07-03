package ru.itis.pizza_fast.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.pizza_fast.model.*;
import ru.itis.pizza_fast.repository.CartItemRepository;
import ru.itis.pizza_fast.repository.OrderRepository;
import ru.itis.pizza_fast.service.AddressService;
import ru.itis.pizza_fast.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final AddressService addressService;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public Order createOrderFromCart(User user, String addressStr) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Корзина пользователя пуста");
        }

        List<OrderDetails.Product> products = cartItems.stream()
                .map(item -> {
                    Pizza pizza = item.getPizza();
                    return new OrderDetails.Product(pizza.getName(), item.getQuantity(), pizza.getPrice());
                })
                .collect(Collectors.toList());

        double totalPrice = products.stream()
                .mapToDouble(p -> p.getQuantity() * p.getPrice())
                .sum();

        OrderDetails details = new OrderDetails(products, totalPrice);

        Address address = addressService.getOrCreateAddress(addressStr);

        Order order = Order.builder()
                .user(user)
                .details(details)
                .address(address)
                .build();

        Order savedOrder = orderRepository.save(order);

        cartItemRepository.deleteByUser(user);

        return savedOrder;
    }
}
