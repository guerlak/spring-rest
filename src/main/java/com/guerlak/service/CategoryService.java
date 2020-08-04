package com.guerlak.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.guerlak.model.Category;
import com.guerlak.repositories.CategoryRepo;
import com.guerlak.service.exceptions.DataIntegrityException;
import com.guerlak.service.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo repo;

	public List<Category> findAll() {
		return repo.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Category create(Category cat) {
		cat.setId(null);
		Category categotyCreated = repo.save(cat);
		return categotyCreated;
	}

	public Category update(Category cat) {
		findById(cat.getId());
		Category categotyCreated = repo.save(cat);
		return categotyCreated;
	}

	public void delete(Long id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Delete not allowed, this category is working with other entity(ies)");
		}
	}
}
