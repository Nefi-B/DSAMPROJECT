package com.assignment.onlineShop.service;

import com.assignment.onlineShop.repository.OrderRepository;
import com.assignment.onlineShop.repository.entity.WebUser;
import com.assignment.onlineShop.service.mapper.OrderMapper;
import com.assignment.onlineShop.service.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderDto orderDto) {
        orderRepository.save(OrderMapper.INSTANCE.toEntity(orderDto));
    }

    public OrderDto findById(Long id) {
        var order = orderRepository.findById(id).orElse(null);
        return OrderMapper.INSTANCE.toDto(order);
    }

    public List<OrderDto> findAll(WebUser user) {
        var orders = orderRepository.findByUserId(user.getId());
        return OrderMapper.INSTANCE.toDtos(orders);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
