package com.guerlak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guerlak.model.Order;
import com.guerlak.repositories.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo repo;

	public List<Order> findAll() {
		return repo.findAll();
	}

	public Order findById(Long id) {

		Optional<Order> obj = repo.findById(id);

		return obj.get();

	}

}
