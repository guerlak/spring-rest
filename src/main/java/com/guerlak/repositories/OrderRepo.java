package com.guerlak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guerlak.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
