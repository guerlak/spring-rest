package com.guerlak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guerlak.model.Product;
import com.guerlak.repositories.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repo;

	public List<Product> findAll() {
		return repo.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> obj = repo.findById(id);
		return obj.get();
	}

}
