package com.assignment.onlineShop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.assignment.onlineShop.repository.CrateRepository;
import com.assignment.onlineShop.repository.entity.Crate;
import com.assignment.onlineShop.service.model.CrateDto;

class CrateServiceTest {

    private CrateRepository crateRepository;
    private CrateService crateService;

    @BeforeEach
    void setUp() {
        crateRepository = Mockito.mock(CrateRepository.class);
        crateService = new CrateService(crateRepository);
    }

    @Test
    void getAll_ReturnsListOfCrates() {
        // Arrange
        var mockCrates = List.of(
                Crate.builder().id(1L).name("Crate 1").price(10.0).build(),
                Crate.builder().id(2L).name("Crate 2").price(15.0).build());
        when(crateRepository.findAll()).thenReturn(mockCrates);

        // Act
        var crateDtos = crateService.getAll();

        // Assert
        assertNotNull(crateDtos);
        assertEquals(2, crateDtos.size());
        assertEquals("Crate 1", crateDtos.get(0).getName());
        verify(crateRepository, times(1)).findAll();
    }

    @Test
    void getCrateById_CrateExists_ReturnsCrate() {
        // Arrange
        var mockCrate = Crate.builder().id(1L).name("Crate 1").price(10.0).build();
        when(crateRepository.findById(1L)).thenReturn(Optional.of(mockCrate));

        // Act
        var crateDto = crateService.getCrateById(1L);

        // Assert
        assertNotNull(crateDto);
        assertEquals("Crate 1", crateDto.getName());
        verify(crateRepository, times(1)).findById(1L);
    }

    @Test
    void getCrateById_CrateDoesNotExist_ThrowsException() {
        // Arrange
        when(crateRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        var exception = assertThrows(RuntimeException.class, () -> crateService.getCrateById(1L));
        assertEquals("Crate not found", exception.getMessage());
        verify(crateRepository, times(1)).findById(1L);
    }

    @Test
    void saveCrate_ReturnsSavedCrate() {
        // Arrange
        var crateDto = CrateDto.builder().id(1L).name("Crate 1").price(10.0).build();
        var mockCrate = Crate.builder().id(1L).name("Crate 1").price(10.0).build();

        when(crateRepository.save(any(Crate.class))).thenReturn(mockCrate);

        // Act
        var savedCrate = crateService.saveCrate(crateDto);

        // Assert
        assertNotNull(savedCrate);
        assertEquals("Crate 1", savedCrate.getName());
        verify(crateRepository, times(1)).save(any(Crate.class));
    }

    @Test
    void deleteCrate_CallsRepositoryDeleteById() {
        // Act
        crateService.deleteCrate(1L);

        // Assert
        verify(crateRepository, times(1)).deleteById(1L);
    }
}
