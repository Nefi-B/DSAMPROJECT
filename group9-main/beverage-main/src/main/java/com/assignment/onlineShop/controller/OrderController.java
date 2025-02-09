package com.assignment.onlineShop.controller;

import com.assignment.onlineShop.repository.entity.ShopOrder;
import com.assignment.onlineShop.repository.entity.WebUser;
import com.assignment.onlineShop.service.OrderService;
import com.assignment.onlineShop.service.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders(Model model, @AuthenticationPrincipal WebUser user) {
        model.addAttribute("orders", orderService.findAll(user));
        return "orders";
    }

    @GetMapping("/getById")
    public OrderDto getOrderById(Long id) {
        return orderService.findById(id);
    }

    @PostMapping
    public void createOrder(@RequestBody OrderDto shopOrder) {
        orderService.save(shopOrder);
    }

    @PutMapping
    public void updateOrder(@RequestBody OrderDto shopOrder) {
        orderService.save(shopOrder);
    }

    @DeleteMapping
    public void deleteOrderById(Long id) {
        orderService.delete(id);
    }
}
