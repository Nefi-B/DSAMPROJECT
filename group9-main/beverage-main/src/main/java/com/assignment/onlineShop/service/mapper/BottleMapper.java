package com.assignment.onlineShop.service.mapper;

import com.assignment.onlineShop.repository.entity.Bottle;
import com.assignment.onlineShop.service.model.BottleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BottleMapper {
    BottleMapper INSTANCE = Mappers.getMapper(BottleMapper.class);

    BottleDto toDto(Bottle bottle);

    List<BottleDto> toDtos(List<Bottle> bottle);

    Bottle toEntity(BottleDto bottleDto);
}
