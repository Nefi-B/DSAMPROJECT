package com.assignment.onlineShop.service.model;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Component
@SessionScope
public class CartDto {
    private final List<OrderItemDto> items = new ArrayList<>();

    public void addItem(OrderItemDto item) {
        items.add(item);
    }

    public void clearBasket() {
        items.clear();
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(OrderItemDto::getPrice).sum();
    }

    public void removeItem(Long itemId) {
        items.removeIf(o -> Objects.equals(o.getId(), itemId));
    }
}
