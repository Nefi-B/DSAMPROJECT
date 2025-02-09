package com.assignment.onlineShop.service;

import com.assignment.onlineShop.service.model.BottleDto;
import com.assignment.onlineShop.service.model.CartDto;
import com.assignment.onlineShop.service.model.CrateDto;
import com.assignment.onlineShop.service.model.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartDto cart;

    @Autowired
    CartService(CartDto cart) {
        this.cart = cart;
    }

    public void addBottleToBasket(BottleDto bottle) {
        OrderItemDto item = OrderItemDto.builder()
                .id(bottle.getId())
                .position(bottle.getName())
                .price(bottle.getPrice())
                .quantity(bottle.getQuantity())
                .build();
        cart.addItem(item);
    }

    public void addCrateToBasket(CrateDto crate) {
        OrderItemDto item = OrderItemDto.builder()
                .id(crate.getId())
                .position(crate.getName())
                .price(crate.getPrice())
                .build();
        cart.addItem(item);
    }

    public List<OrderItemDto> getBasketItems() {
        return cart.getItems();
    }

    public double getTotalPrice() {
        return cart.getTotalPrice();
    }

    public void clearBasket() {
        cart.clearBasket();
    }

    public void removeItemFromBasket(Long item) {
        cart.removeItem(item);
    }
}

