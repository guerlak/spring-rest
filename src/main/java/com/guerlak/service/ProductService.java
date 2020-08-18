package com.guerlak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.guerlak.model.Category;
import com.guerlak.model.Product;
import com.guerlak.repositories.CategoryRepo;
import com.guerlak.repositories.ProductRepo;
import com.guerlak.service.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repo;	
	
	@Autowired
	private CategoryRepo categoryRepo;

	public Product findById(Long id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Page<Product> search(String name, 
								List<Long> ids,
								Integer page, 
								Integer linesPerPage, 
								String orderBy, 
								String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Category> categories = categoryRepo.findAllById(ids);
		
		return repo.search(name, categories, pageRequest);
		
	}
}
