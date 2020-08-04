package com.guerlak.model.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UserDTO {
	
	private Long id;
	@NotEmpty(message = "preenchimento obrigatório")
	@Length(min=5, max=60, message = "deve ser entre 5 e 60 chars")
	private String name;
	@NotEmpty(message = "preenchimento obrigatório")
	private String email;
	@Length(min=5, max=10, message = "Obrigatório e deve ser entre 5 e 10 chars")
	private String password;
	
	private String phone;

	
	public UserDTO() {
	}

	public UserDTO(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

}
