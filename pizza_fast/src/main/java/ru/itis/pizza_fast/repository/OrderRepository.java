package ru.itis.pizza_fast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.pizza_fast.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
