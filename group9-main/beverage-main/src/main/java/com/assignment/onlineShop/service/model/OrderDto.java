package com.assignment.onlineShop.service.model;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto {
    private Long id;
    private Long userId;
    @Positive
    private Double price;
    private List<OrderItemDto> orderItems;
}
