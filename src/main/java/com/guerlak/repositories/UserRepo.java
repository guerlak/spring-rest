package com.guerlak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guerlak.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
