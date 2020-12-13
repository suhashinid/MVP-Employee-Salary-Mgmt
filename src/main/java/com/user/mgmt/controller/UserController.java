package com.user.mgmt.controller;
/*
 * Authos: Suhashini
 * Class: This is main controller for user to route the request mapping
 */
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.user.mgmt.exception.FileUploadExceptionHandle;
import com.user.mgmt.message.ResponseMessage;
import com.user.mgmt.model.User;
import com.user.mgmt.reader.UserCSVReader;
import com.user.mgmt.service.UserService;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/mvp")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class.getName());

	 @Autowired
	 UserService userService;

	 @RequestMapping(value="/",method=RequestMethod.GET)
	    public String index() {
	        return "index";
	    }
	 
	 @RequestMapping(value="/users/upload",method=RequestMethod.POST)
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    logger.info("uploadingFile: " + file.getName());
	    if (UserCSVReader.hasCSVFormat(file)) {
	      try {
	    	  userService.save(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        logger.info(message+e.getMessage());
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	    }

	    message = "Please upload a csv file!";
	    logger.info("Not CSV file: " + message);
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	  }

	  @RequestMapping(value="/users",method=RequestMethod.GET, headers="Accept=application/json")
	  public ResponseEntity<List<User>> getAllUsers() {
		  logger.info("getAllUsers: start");
	    try {
	      List<User> users = userService.getAllUsers();

	      if (users.isEmpty()) {
	    	  logger.info("getAllUsers: No Users found");
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(users, HttpStatus.OK);
	    } catch (Exception e) {
	    	logger.info("getAllUsers: Exception Occured");
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	  }
	  @RequestMapping(value = "/users/{id}", method=RequestMethod.GET, headers="Accept=application/json")
	  public ResponseEntity<List<User>> getUser(@PathVariable(name="id") String id) {
		  logger.info("getUser:"+id);
	    try {
	      List<User> users = userService.getUserById(id);

	      if (users.isEmpty()) {
	    	  logger.info("getUser: User not found");
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(users, HttpStatus.OK);
	    } catch (Exception e) {
	    	logger.info("getUser: Exception Occured");
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
//		@RequestMapping("/error")
//		public String getErrorPath() {
//			return "<center><h1>Something went wrong</h1></center>";
//		}
}
