package com.guerlak.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.guerlak.model.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
	
	@Query("select a from Address a where a.user.id = ?1")
	List<Address> findByUser(Long id);
}

	


