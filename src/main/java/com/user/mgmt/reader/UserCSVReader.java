package com.user.mgmt.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.user.mgmt.controller.UserController;
import com.user.mgmt.model.User;
import com.user.mgmt.validator.UserValidator;

public class UserCSVReader {
	private static final Logger logger = Logger.getLogger(UserController.class.getName());
	public static String TYPE = "text/csv";
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  static String[] HEADERS = { "Id", "Name", "Login",  "Salary", "StartDate" };

	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static LocalDate getValidStartDate(String startDate) {
	        if (startDate == null) {
	            throw new RuntimeException("Must include Start Date");
	        }
	        try {
	        	//LocalDateTime now = LocalDateTime.now();
	            LocalDate birthdate = LocalDate.parse(startDate, formatter);
	            return birthdate;
	        } catch (Exception e) {
	            throw new RuntimeException("Must include valid start date in yyyy-MM-dd format");
	        }

	    }
	  public static String validateInput(String str)
	  {
		  if (str == null) {
	            throw new RuntimeException("Field should not be null");
	        }
		  try
		  {
			  if(str.startsWith("#"))
					  {
				  return "";
					  }
			  
				  
		  }
		  catch (Exception e) {
	            throw new RuntimeException("Error in Validation");
	        }
		  return str;
	  }
	 
	  public static List<User> csvToUsers(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<User> users = new ArrayList<User>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {
	    	  User user = new User(
	    			  validateInput(csvRecord.get("Id")),
	    			  validateInput(csvRecord.get("Login")),
	    			  validateInput(csvRecord.get("Name")),
	              Double.valueOf(validateInput(csvRecord.get("Salary"))),
	              getValidStartDate(validateInput(csvRecord.get("StartDate")))
	            );
	    	  logger.info(""+user);
	    	 
	    	  users.add(user);
	      }
	      logger.info(""+users);
	      return users;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }

	  
}
