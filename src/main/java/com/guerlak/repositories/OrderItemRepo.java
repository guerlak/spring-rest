package com.guerlak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guerlak.model.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}
