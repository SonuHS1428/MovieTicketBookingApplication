package com.example.demo.movies.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.movies.entity.Order;
import com.example.demo.movies.service.OrderService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/api/v1/order")
    public ResponseEntity<Order> newOrder(@Valid @RequestBody Order newOrder) {
        Order savedOrder = orderService.saveOrder(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/api/v1/order/{userId}")
    public ResponseEntity<Optional<Order>> getLastOrderByUserId(@PathVariable Long userId) {
        Optional<Order> order = orderService.getLastOrderByUserId(userId);
        if (order.isPresent()) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
