package com.assignment.onlineShop.service;

import com.assignment.onlineShop.service.model.BottleDto;
import com.assignment.onlineShop.service.model.CartDto;
import com.assignment.onlineShop.service.model.CrateDto;
import com.assignment.onlineShop.service.model.OrderItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartDto cartMock;

    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartService = new CartService(cartMock);
    }

    @Test
    void addBottleToBasket() {
        BottleDto bottle = BottleDto.builder()
                .id(1L)
                .name("Water Bottle")
                .price(2.5)
                .quantity(2)
                .build();

        cartService.addBottleToBasket(bottle);

        verify(cartMock).addItem(argThat(item ->
                item.getId().equals(1L) &&
                        item.getPosition().equals("Water Bottle") &&
                        item.getPrice() == 2.5 &&
                        item.getQuantity() == 2
        ));
    }

    @Test
    void addCrateToBasket() {
        CrateDto crate = CrateDto.builder()
                .id(2L)
                .name("Beer Crate")
                .price(12.5)
                .build();

        cartService.addCrateToBasket(crate);

        verify(cartMock).addItem(argThat(item ->
                item.getId().equals(2L) &&
                        item.getPosition().equals("Beer Crate") &&
                        item.getPrice() == 12.5
        ));
    }

    @Test
    void getBasketItems() {
        List<OrderItemDto> mockItems = List.of(
                OrderItemDto.builder().id(1L).position("Water Bottle").price(2.5).quantity(2).build(),
                OrderItemDto.builder().id(2L).position("Beer Crate").price(12.5).quantity(1).build()
        );

        when(cartMock.getItems()).thenReturn(mockItems);

        List<OrderItemDto> items = cartService.getBasketItems();

        assertEquals(2, items.size());
        assertEquals("Water Bottle", items.get(0).getPosition());
        assertEquals(12.5, items.get(1).getPrice());

        verify(cartMock).getItems();
    }

    @Test
    void getTotalPrice() {
        when(cartMock.getTotalPrice()).thenReturn(15.0);

        double totalPrice = cartService.getTotalPrice();

        assertEquals(15.0, totalPrice);

        verify(cartMock).getTotalPrice();
    }

    @Test
    void clearBasket() {
        cartService.clearBasket();

        verify(cartMock).clearBasket();
    }

    @Test
    void removeItemFromBasket() {
        Long itemId = 1L;

        cartService.removeItemFromBasket(itemId);

        verify(cartMock).removeItem(itemId);
    }
}
