package com.guerlak.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guerlak.model.Address;
import com.guerlak.model.User;
import com.guerlak.model.dto.NewUserDTO;
import com.guerlak.model.enums.UserType;
import com.guerlak.repositories.AddressRepo;
import com.guerlak.repositories.UserRepo;
import com.guerlak.service.exceptions.DataIntegrityException;
import com.guerlak.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AddressRepo addressRepo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	@Transactional
	public User addUser(User u) {
		u.setId(null);
		u = repo.save(u);
		addressRepo.saveAll(u.getAdresses());
		return repo.save(u);
	}

	public User updateUser(Long id, User user) {
		try {
			User entity = repo.getOne(id);
			this.updateData(entity, user);
			return repo.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void updateData(User entity, User u) {
		entity.setEmail(u.getEmail());
		entity.setName(u.getName());
		entity.setPhones(u.getPhones());
	}
	
	public void deleteUser(Long id) {
		try {
			repo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("There are orders relation. Entity can not be deleted.");
		}
	}

	public User fromDTO(NewUserDTO userDTO) {
		User user = new User(null, userDTO.getName(), 
				userDTO.getEmail(), 
				userDTO.getPassword(), 
				UserType.valueOf(userDTO.getUserType()));
		
		Address address = new Address(user, 
				userDTO.getCity(), 
				userDTO.getState(), 
				userDTO.getComplement(),
				userDTO.getCep());
		
		user.getAdresses().add(address);
		user.getPhones().add(userDTO.getPhone1());

		if (userDTO.getPhone2() != null) {
			user.getPhones().add(userDTO.getPhone2());
		}

		if (userDTO.getPhone3() != null) {
			user.getPhones().add(userDTO.getPhone3());
		}
		return user;
	}

}
