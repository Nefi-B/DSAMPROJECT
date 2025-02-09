package com.assignment.onlineShop.service.model;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemDto {
    private Long id;
    private String position;
    @Positive
    private Double price;
    private Integer quantity;
}
