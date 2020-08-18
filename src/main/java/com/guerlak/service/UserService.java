package com.guerlak.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guerlak.model.Address;
import com.guerlak.model.User;
import com.guerlak.model.dto.NewUserDTO;
import com.guerlak.model.dto.UserDTO;
import com.guerlak.model.enums.Profile;
import com.guerlak.model.enums.UserType;
import com.guerlak.repositories.AddressRepo;
import com.guerlak.repositories.UserRepo;
import com.guerlak.security.UserSS;
import com.guerlak.service.exceptions.AuthorizationException;
import com.guerlak.service.exceptions.DataIntegrityException;
import com.guerlak.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AddressRepo addressRepo;

	public User findById(Long id) {
		
		UserSS userSS = AuthService.authenticated();
		
		if(userSS == null || !userSS.hasRole(Profile.ADMIN) && !id.equals(userSS.getId())) {
			throw new AuthorizationException("Access denied");
		}
		
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public User addUser(User u) {
		u.setId(null);
		u = repo.save(u);
		addressRepo.saveAll(u.getAddresses());
		return repo.save(u);
	}

	public User updateUser(Long id, UserDTO dto) {
		try {
			User entity = repo.getOne(id);
			this.updateData(entity, dto);
			return repo.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void updateData(User entity, UserDTO u) {
		entity.setEmail(u.getEmail());
		entity.setName(u.getName());
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
				bcrypt.encode(userDTO.getPassword()), 
				UserType.valueOf(userDTO.getUserType()));
		
		Address address = new Address(user, 
					userDTO.getCity(), 
					userDTO.getState(), 
					userDTO.getComplement(),
					userDTO.getCep()
				);
		
		user.getAddresses().add(address);
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
