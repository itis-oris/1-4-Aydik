package ru.itis.pizza_fast.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetails {
    private List<Product> products;
    private double totalPrice;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Product {
        private String name;
        private int quantity;
        private double price;
    }
}
