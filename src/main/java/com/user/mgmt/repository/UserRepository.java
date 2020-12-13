package com.user.mgmt.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.mgmt.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

	List<User> findById(String id);

	//void saveAll(List<User> users);
  
	List<User> findAll();
}

