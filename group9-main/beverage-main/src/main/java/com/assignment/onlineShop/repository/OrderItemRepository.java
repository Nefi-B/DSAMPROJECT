package com.assignment.onlineShop.repository;

import com.assignment.onlineShop.repository.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
