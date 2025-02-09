package com.assignment.onlineShop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.assignment.onlineShop.repository.entity.UserRepository;
import com.assignment.onlineShop.repository.entity.WebUser;

class UserServiceTest {

    private UserRepository userRepo;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepo = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepo);
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        // Arrange
        String username = "testUser";
        WebUser mockUser = WebUser.builder()
                .username(username)
                .password("password")
                .build();

        when(userRepo.findUserByUsername(username)).thenReturn(mockUser);

        // Act
        UserDetails userDetails = userService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        verify(userRepo, times(1)).findUserByUsername(username);
    }

    @Test
    void loadUserByUsername_UserDoesNotExist_ThrowsException() {
        // Arrange
        String username = "nonExistentUser";
        when(userRepo.findUserByUsername(username)).thenReturn(null);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
        verify(userRepo, times(1)).findUserByUsername(username);
    }
}
