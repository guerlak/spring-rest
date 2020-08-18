package com.guerlak.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.guerlak.model.Address;
import com.guerlak.model.Order;
import com.guerlak.model.OrderItem;
import com.guerlak.model.User;
import com.guerlak.model.dto.NewOrderDTO;
import com.guerlak.model.enums.OrderStatus;
import com.guerlak.repositories.OrderItemRepo;
import com.guerlak.repositories.OrderRepo;
import com.guerlak.security.UserSS;
import com.guerlak.service.exceptions.AuthorizationException;
import com.guerlak.service.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepo repo;

	@Autowired
	private OrderItemRepo orderItemRepo;

	@Autowired
	private ProductService prodService;
	
	@Autowired
	private UserService userService;

	public Order findById(Long id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	

	public Page<Order> findPage(Integer page, 
								Integer linesPerPage, 
								String orderBy, 
								String direction) {
		
		UserSS userSS = AuthService.authenticated();
		
		if(userSS == null) {
			throw new AuthorizationException("Access denied");
		}
		
		User user = userService.findById(userSS.getId());
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findByClient(user, pageRequest);
	
	}

	@Transactional
	public Order createOrder(NewOrderDTO dto) {

		User user = userService.findById(dto.getClient().getId());
		
		List<Address> adr = user.getAddresses()
				.stream()
				.filter(a -> a.getId() == dto.getAddress().getId())
				.collect(Collectors.toList());
		
		user.setAddresses(adr);
		
		Order order = new Order(null, Instant.now(), user, OrderStatus.WAITING_PAYMENT);
		repo.save(order);
		
		for (OrderItem orderItem : dto.getOrderItens()) {
			orderItem.setProduct(prodService.findById(orderItem.getProduct().getId()));
			orderItem.setPrice(orderItem.getProduct().getPrice());
			orderItem.setOrder(order);
		}
		
		orderItemRepo.saveAll(dto.getOrderItens());
		order.setItems(dto.getOrderItens());
		return order;
	}
}
