package com.guerlak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guerlak.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
