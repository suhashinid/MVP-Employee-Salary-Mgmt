package com.user.mgmt.validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

import javax.validation.ConstraintValidatorContext;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.user.mgmt.model.User;


public class UserValidator implements Validator {

	 /**
     * This Validator validates *just* Person instances
     */
    public boolean supports(Class user) {
        return User.class.equals(user);
    }
//	User user1 = new User("emp1", "emp1", "Marie", 8000.00,new LocalDate("2020-01-01"));
//	User user2 = new User("emp2", "emp2", "Satya", 6000.00,new LocalDate("2020-06-01"));
//   
//    List<User> userList = Arrays.asList(user1, user2);
//    


    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "id", "id.empty");
        ValidationUtils.rejectIfEmpty(e, "login", "login.empty");
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        ValidationUtils.rejectIfEmpty(e, "salary", "salary.empty");
        ValidationUtils.rejectIfEmpty(e, "startDate", "startdate.empty");
        User p = (User) obj;
        if (p.getSalary() < 0) {
            e.rejectValue("age", "negativevalue");
        } 
    }
    
}
