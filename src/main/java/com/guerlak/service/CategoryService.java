package com.guerlak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guerlak.model.Category;
import com.guerlak.repositories.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo repo;

	public List<Category> findAll() {
		return repo.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> obj = repo.findById(id);
		return obj.get();
	}

}
