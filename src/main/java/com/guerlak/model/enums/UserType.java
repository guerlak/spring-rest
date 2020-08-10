package com.guerlak.model.enums;

public enum UserType {
	
	PESSOA_FISICA(1),
	PESSOA_JURIDICA(2);
	
	private int code;
	
	UserType(int n){
		this.code = n;
	}
	
	public int getCode() {
		return code;
	}

	public static UserType valueOf(int code) {
		for (UserType value: UserType.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid UserType code");
	}

}
