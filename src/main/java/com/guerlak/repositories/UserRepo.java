package com.guerlak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.guerlak.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	@Transactional(readOnly =true)
	User findByEmail(String email);
	
}
