package com.user.mgmt.service;

/*
 * @Author: Suhashini
 * @Service: service class has 3 methods to implement CRUD of User
 */
import java.io.IOException;
import java.util.List;


import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.user.mgmt.model.User;
import com.user.mgmt.reader.UserCSVReader;
import com.user.mgmt.repository.UserRepository;
//import com.appliances.app.exceptionhandler.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	 public void save(MultipartFile file) {
		    try {
		      List<User> users = UserCSVReader.csvToUsers(file.getInputStream());
		      userRepository.saveAll(users);
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		    }
		  }

		  public List<User> getAllUsers() {
		    return userRepository.findAll();
		  }
		  
		  public List<User> getUserById(String id) {
			    return userRepository.findById(id);
			    		// .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));;
			  }
		  
}
