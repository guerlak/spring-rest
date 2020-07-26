package com.guerlak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guerlak.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
