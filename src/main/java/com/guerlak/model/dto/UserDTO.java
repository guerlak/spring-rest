package com.guerlak.model.dto;

import java.util.List;
import com.guerlak.model.Address;
import com.guerlak.model.User;
import com.guerlak.model.enums.UserType;

public class UserDTO {
	
	private Long id;
	private String name;
	private String email;
	private String phone;
	private UserType userType;
	
	private List<Address> addresses;

	public UserDTO() {}

	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.addresses = user.getAdresses();
		this.userType = UserType.valueOf(user.getUserType());
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
	
	public String getPhone() {
		return phone;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
}
