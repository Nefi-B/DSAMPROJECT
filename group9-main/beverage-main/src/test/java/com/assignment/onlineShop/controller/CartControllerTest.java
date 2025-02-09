//package com.assignment.onlineShop.controller;
//import org.junit.jupiter.api.BeforeEach;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.mockito.ArgumentMatchers.any;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import java.util.List;
//
//import com.assignment.onlineShop.repository.entity.WebUser;
//import com.assignment.onlineShop.service.CartService;
//import com.assignment.onlineShop.service.OrderService;
//import com.assignment.onlineShop.service.model.OrderDto;
//import com.assignment.onlineShop.service.model.OrderItemDto;
//
//
//@WebMvcTest(CartController.class)
//class CartControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private CartService cartService;
//
//    @Mock
//    private OrderService orderService;
//
//    private List<OrderItemDto> mockItems;
//    private WebUser mockUser;
//
//    @BeforeEach
//    void setUp() {
//        mockItems = List.of(
//                OrderItemDto.builder().id(1L).position("Bottle A").price(10.0).quantity(2).build(),
//                OrderItemDto.builder().id(2L).position("Bottle B").price(15.0).quantity(1).build()
//        );
//
//        mockUser = WebUser.builder().id(1L).username("testUser").build();
//    }
//
//    @Test
//    @WithMockUser
//    void showBasket_ReturnsCartPageWithItems() throws Exception {
//        // Arrange
//        when(cartService.getBasketItems()).thenReturn(mockItems);
//        when(cartService.getTotalPrice()).thenReturn(35.0);
//
//        // Act & Assert
//        mockMvc.perform(get("/cart"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("cart"))
//                .andExpect(model().attribute("items", mockItems))
//                .andExpect(model().attribute("totalPrice", 35.0));
//
//        verify(cartService, times(1)).getBasketItems();
//        verify(cartService, times(1)).getTotalPrice();
//    }
//
//    @Test
//    @WithMockUser
//    void submitOrder_SubmitsOrderAndClearsBasket() throws Exception {
//        // Arrange
//        when(cartService.getBasketItems()).thenReturn(mockItems);
//        when(cartService.getTotalPrice()).thenReturn(35.0);
//
//        // Act & Assert
//        mockMvc.perform(post("/cart/submitOrder"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("orderConfirmation"))
//                .andExpect(model().attribute("message", "Order submitted successfully!"));
//
//        verify(orderService, times(1)).save(any(OrderDto.class));
//        verify(cartService, times(1)).clearBasket();
//    }
//
//    @Test
//    @WithMockUser
//    void removeCart_RemovesItemAndRedirectsToCart() throws Exception {
//        // Act & Assert
//        mockMvc.perform(post("/cart/remove/1"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/cart"));
//
//        verify(cartService, times(1)).removeItemFromBasket(1L);
//    }
//}
