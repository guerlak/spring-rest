package com.guerlak.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.guerlak.model.User;
import com.guerlak.repositories.UserRepo;
import com.guerlak.service.exceptions.DatabaseException;
import com.guerlak.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User addUser(User u){
		return repo.save(u);
	}
	
	public void deleteUser(Long id) {
		try {
			repo.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User updateUser(Long id, User user) {
		try {
			User entity = repo.getOne(id);
			this.updateData(entity, user);
			return repo.save(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public void updateData(User entity, User u) {
		entity.setEmail(u.getEmail());
		entity.setName(u.getName());
		entity.setPhone(u.getPhone());
	}
	
}
