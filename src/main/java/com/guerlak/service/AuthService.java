package com.guerlak.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.guerlak.security.UserSS;

public class AuthService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder
					.getContext()
					.getAuthentication()
					.getPrincipal();
			
		}catch (Exception e) {
			return null;
		}
		
	}

}
