package com.assignment.onlineShop.service;

import com.assignment.onlineShop.repository.CrateRepository;
import com.assignment.onlineShop.service.mapper.CrateMapper;
import com.assignment.onlineShop.service.model.CrateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrateService {
    private final CrateRepository crateRepository;

    @Autowired
    CrateService(CrateRepository crateRepository) {
        this.crateRepository = crateRepository;
    }

    public List<CrateDto> getAll() {
        var crates = crateRepository.findAll();
        return CrateMapper.INSTANCE.toDto(crates);
    }

    public CrateDto getCrateById(Long id) {
        var crate = crateRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Crate not found"));
        return CrateMapper.INSTANCE.toDto(crate);
    }

    public CrateDto saveCrate(CrateDto crateDto) {
        var crate = crateRepository.save(CrateMapper.INSTANCE.toEntity(crateDto));
        return CrateMapper.INSTANCE.toDto(crate);
    }

    public void deleteCrate(Long id) {
        crateRepository.deleteById(id);
    }
}
