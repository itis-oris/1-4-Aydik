package ru.itis.pizza_fast.repository;

import ru.itis.pizza_fast.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.pizza_fast.model.User;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);

    @org.springframework.data.jpa.repository.Query("SELECT COALESCE(SUM(c.quantity), 0) FROM CartItem c WHERE c.user = :user")
    int getTotalQuantityByUser(User user);

    void deleteByUser(User user);
} 