package com.guerlak.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.guerlak.model.User;
import com.guerlak.service.validation.UserUpdate;

@UserUpdate
public class UserDTO {
	
private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=60, message = "deve ser entre 5 e 60 chars")
	private String name;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=20, message = "deve ser entre 5 e 20 chars")
	@Email
	private String email;

	public UserDTO() {
	}
	
	public UserDTO(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
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
}
