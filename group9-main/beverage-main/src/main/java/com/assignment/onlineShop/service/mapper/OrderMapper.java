package com.assignment.onlineShop.service.mapper;

import com.assignment.onlineShop.repository.entity.ShopOrder;
import com.assignment.onlineShop.service.model.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(ShopOrder crate);

    List<OrderDto> toDtos(List<ShopOrder> crate);

    ShopOrder toEntity(OrderDto crateDto);
}
