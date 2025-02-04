package com.example.demo.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.movies.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findFirstByCustomerIdOrderByCreatedAtDesc(Long customerId);
}
