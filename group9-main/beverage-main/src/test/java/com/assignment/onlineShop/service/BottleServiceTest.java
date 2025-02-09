package com.assignment.onlineShop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.assignment.onlineShop.repository.BottleRepository;
import com.assignment.onlineShop.repository.entity.Bottle;
import com.assignment.onlineShop.service.model.BottleDto;

class BottleServiceTest {

    private BottleRepository bottleRepository;
    private BottleService bottleService;

    @BeforeEach
    void setUp() {
        bottleRepository = Mockito.mock(BottleRepository.class);
        bottleService = new BottleService(bottleRepository);
    }

    @Test
    void save_ReturnsSavedBottleDto() {
        // Arrange
        var bottleDto = BottleDto.builder().id(1L).name("Bottle 1").price(10.0).inStock(10).build();
        var mockBottle = Bottle.builder().id(1L).name("Bottle 1").price(10.0).inStock(10).build();

        when(bottleRepository.save(any(Bottle.class))).thenReturn(mockBottle);

        // Act
        var savedBottleDto = bottleService.save(bottleDto);

        // Assert
        assertNotNull(savedBottleDto);
        assertEquals("Bottle 1", savedBottleDto.getName());
        verify(bottleRepository, times(1)).save(any(Bottle.class));
    }

    @Test
    void getAll_ReturnsListOfBottleDtos() {
        // Arrange
        var mockBottles = List.of(
                Bottle.builder().id(1L).name("Bottle 1").price(10.0).inStock(10).build(),
                Bottle.builder().id(2L).name("Bottle 2").price(8.0).inStock(20).build()
        );
        when(bottleRepository.findAll()).thenReturn(mockBottles);

        // Act
        var bottleDtos = bottleService.getAll();

        // Assert
        assertNotNull(bottleDtos);
        assertEquals(2, bottleDtos.size());
        assertEquals("Bottle 1", bottleDtos.get(0).getName());
        verify(bottleRepository, times(1)).findAll();
    }

    @Test
    void findById_BottleExists_ReturnsBottleDto() {
        // Arrange
        var mockBottle = Bottle.builder().id(1L).name("Bottle 1").price(5.0).inStock(10).build();
        when(bottleRepository.findById(1L)).thenReturn(Optional.of(mockBottle));

        // Act
        var bottleDto = bottleService.findById(1L);

        // Assert
        assertNotNull(bottleDto);
        assertEquals("Bottle 1", bottleDto.getName());
        verify(bottleRepository, times(1)).findById(1L);
    }

    @Test
    void findById_BottleDoesNotExist_ThrowsException() {
        // Arrange
        when(bottleRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        var exception = assertThrows(RuntimeException.class, () -> bottleService.findById(1L));
        assertEquals("Bottle not found", exception.getMessage());
        verify(bottleRepository, times(1)).findById(1L);
    }

    @Test
    void delete_CallsRepositoryDeleteById() {
        // Act
        bottleService.delete(1L);

        // Assert
        verify(bottleRepository, times(1)).deleteById(1L);
    }
}
