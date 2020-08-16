package com.guerlak.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.guerlak.service.validation.UserInsert;

@UserInsert
public class NewUserDTO implements Serializable {	

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=60, message = "deve ser entre 5 e 60 chars")
	private String name;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=20, message = "deve ser entre 5 e 20 chars")
	@Email
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String password;
	
	private String userType;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=3, max=20, message = "deve ser entre 3 e 20 chars")
	private String city;
	private String state;
	private String complement;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=7, max=10, message = "deve ser entre 9 e 11 chars")
	private String cep;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=10, message = "deve ser entre 5 e 10 chars")
	private String phone1;
	private String phone2;
	private String phone3;

	private String cpfCnpj;
	
	public NewUserDTO() {
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
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

}
