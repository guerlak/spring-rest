package com.guerlak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.guerlak.model.Category;


public interface CategoryRepo extends JpaRepository<Category, Long> {
//	@Query("select c from Category c where c.name = ?1")
	Category findByName(String name);
	
}


