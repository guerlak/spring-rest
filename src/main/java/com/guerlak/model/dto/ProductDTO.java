package com.guerlak.model.dto;

import java.io.Serializable;

import com.guerlak.model.Product;

public class ProductDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Double price;

	public ProductDTO() {
	}
	
	public ProductDTO(Product prod) {
		this.id = prod.getId();
		this.name = prod.getName();
		this.price = prod.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
