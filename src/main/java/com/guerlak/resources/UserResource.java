package com.guerlak.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guerlak.model.User;
import com.guerlak.model.dto.NewUserDTO;
import com.guerlak.model.dto.UserDTO;
import com.guerlak.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User u = service.findById(id);
		UserDTO dto = new UserDTO(u);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<User>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		Page<User> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<User> addUser(@Valid @RequestBody NewUserDTO userDTO) {
		User u = service.fromDTO(userDTO);
		User res = service.addUser(u);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(res.getId()).toUri();
		return ResponseEntity.created(uri).body(res);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
		service.updateUser(id, dto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
