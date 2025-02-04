package com.example.demo.service;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginUser() {
        User mockUser = new User(1L, "John", "Doe", "john@example.com", "password");
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(mockUser));
        
        User user = userService.getUserByEmail("john@example.com");

        assertNotNull(user);
    }

    
}
