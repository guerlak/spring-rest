package com.guerlak.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.guerlak.model.Order;
import com.guerlak.model.User;


public interface OrderRepo extends JpaRepository<Order, Long> {
	@Transactional
	Page<Order> findByClient(User client, Pageable pageRequest);
}
