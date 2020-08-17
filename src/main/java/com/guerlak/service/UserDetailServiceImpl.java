package com.guerlak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.guerlak.model.User;
import com.guerlak.repositories.UserRepo;
import com.guerlak.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User u = 	repo.findByEmail(email);
		
		if(u == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(u.getId(), u.getEmail(), u.getPassword(), u.getProfiles());
	}
}
