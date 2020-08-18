package com.guerlak.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guerlak.security.JWTUtil;
import com.guerlak.security.UserSS;
import com.guerlak.service.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refresToken(HttpServletResponse res){
		UserSS userSS = AuthService.authenticated();
		String token = jwtUtil.generateToken(userSS.getUsername());
		res.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

}
