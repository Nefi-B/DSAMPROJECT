package com.assignment.onlineShop.controller;

import com.assignment.onlineShop.repository.entity.WebUser;
import com.assignment.onlineShop.service.CartService;
import com.assignment.onlineShop.service.OrderService;
import com.assignment.onlineShop.service.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;

    @Autowired
    CartController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public String showBasket(Model model) {
        model.addAttribute("items", cartService.getBasketItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "cart";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(Model model, @AuthenticationPrincipal WebUser user) {
        var items = cartService.getBasketItems();
        var totalPrice = cartService.getTotalPrice();
        var order = OrderDto.builder()
                .userId(user.getId())
                .orderItems(items)
                .price(totalPrice)
                .build();

        orderService.save(order);
        cartService.clearBasket();
        model.addAttribute("message", "Order submitted successfully!");
        return "orderConfirmation";
    }

    @PostMapping("/remove/{id}")
    public String removeCart(@PathVariable Long id) {
        cartService.removeItemFromBasket(id);
        return "redirect:/cart";
    }
}

