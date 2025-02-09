package com.assignment.onlineShop.service.mapper;

import com.assignment.onlineShop.repository.entity.Crate;
import com.assignment.onlineShop.service.model.CrateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CrateMapper {
    CrateMapper INSTANCE = Mappers.getMapper(CrateMapper.class);

    CrateDto toDto(Crate crate);

    List<CrateDto> toDto(List<Crate> crate);

    Crate toEntity(CrateDto crateDto);
}
