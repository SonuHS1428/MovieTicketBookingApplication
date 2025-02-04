
package com.example.demo.movies.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.movies.entity.Order;
import com.example.demo.movies.repository.OrderRepository;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    public OrderServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder() {
        Order mockOrder = new Order(1L, new Date(), 1L, 1L, "Inception", "English", "Evening", 100.0, Arrays.asList(1, 2, 3));
        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);

        Order order = orderService.saveOrder(mockOrder);

        assertNotNull(order);
    }
}
