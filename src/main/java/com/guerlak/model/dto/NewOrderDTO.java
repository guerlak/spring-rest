package com.guerlak.model.dto;

import java.util.List;

import com.guerlak.model.Address;
import com.guerlak.model.OrderItem;
import com.guerlak.model.Payment;
import com.guerlak.model.User;

public class NewOrderDTO {
	
	private User client;
	private int clientAddress;
	private Payment payment;
	
	private List<OrderItem> orderItens;
	
	private Address address;
	
	public NewOrderDTO() {}

	
	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public int getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(int clientAddress) {
		this.clientAddress = clientAddress;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<OrderItem> getOrderItens() {
		return orderItens;
	}

	public void setOrderItens(List<OrderItem> orderItens) {
		this.orderItens = orderItens;
	}

}
