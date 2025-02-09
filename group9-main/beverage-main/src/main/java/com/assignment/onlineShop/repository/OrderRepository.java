package com.assignment.onlineShop.repository;

import com.assignment.onlineShop.repository.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<ShopOrder, Long> {
    List<ShopOrder> findByUserId(Long userId);
}
