package com.example.orderpractice.order.repository;

import com.example.orderpractice.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
