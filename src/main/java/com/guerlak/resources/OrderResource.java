package com.guerlak.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guerlak.model.Order;
import com.guerlak.model.dto.NewOrderDTO;
import com.guerlak.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable Long id) {
		Order u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}
	
	@GetMapping
	public ResponseEntity<Page<Order>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="direction", defaultValue="DESC")String direction,
			@RequestParam(value="orderBy", defaultValue="moment")String orderBy
			) {
		Page<Order> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody NewOrderDTO newOrderDto){
		Order res = service.createOrder(newOrderDto);
		return ResponseEntity.ok().body(res);
	}
	
}
