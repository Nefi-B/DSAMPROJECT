package com.assignment.onlineShop.service;

import com.assignment.onlineShop.repository.BottleRepository;
import com.assignment.onlineShop.service.mapper.BottleMapper;
import com.assignment.onlineShop.service.model.BottleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BottleService {
    private final BottleRepository bottleRepository;

    @Autowired
    BottleService(BottleRepository bottleRepository) {
        this.bottleRepository = bottleRepository;
    }

    public BottleDto save(BottleDto bottleDto) {
        var bottle = bottleRepository.save(BottleMapper.INSTANCE.toEntity(bottleDto));
        return BottleMapper.INSTANCE.toDto(bottle);
    }

    public List<BottleDto> getAll() {
        var bottle = bottleRepository.findAll();
        return BottleMapper.INSTANCE.toDtos(bottle);

    }

    public BottleDto findById(Long id) {
        var bottle = bottleRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Bottle not found"));
        return BottleMapper.INSTANCE.toDto(bottle);
    }

    public void delete(Long id) {
        bottleRepository.deleteById(id);
    }
}
