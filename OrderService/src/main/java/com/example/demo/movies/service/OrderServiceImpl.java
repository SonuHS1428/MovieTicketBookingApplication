package com.example.demo.movies.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.movies.entity.Order;
import com.example.demo.movies.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Override
    public Optional<Order> getLastOrderByUserId(Long userId) {
        return orderRepository.findFirstByCustomerIdOrderByCreatedAtDesc(userId);
    }
}
