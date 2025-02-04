package com.example.demo.movies.service;

import java.util.Optional;

import com.example.demo.movies.entity.Order;

public interface OrderService {
    Order saveOrder(Order newOrder);
    Optional<Order> getLastOrderByUserId(Long userId);
}
