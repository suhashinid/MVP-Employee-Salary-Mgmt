package com.user.mgmt.model;


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

/*
 * CREATE TABLE `mydb`.`user` (
  `id` VARCHAR(20) NOT NULL,
  `login` VARCHAR(20) NOT NULL,
  `name` VARCHAR(55) NOT NULL,
  `salary` DECIMAL(10) NOT NULL,
  `start_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);

 */


@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id")
	@NotNull(message = "Id is mandatory")
	private String id;
	
	@Column(name = "login")
	@NotNull(message = "Login is mandatory")
	private String login;
	
	@Column(name = "name")
	@NotNull(message = "Name is mandatory")
	private String name;
	
	@Column(name = "salary")
	@NotNull(message = "Salary is mandatory")
	@DecimalMin("0.0")
	private Double salary;
	
	@Column(name = "start_date")
	@NotNull(message = "Start Date is mandatory")
	private LocalDate startDate;
  
	public User() {}
	public User(String id, String login, String name, Double salary,LocalDate startDate) {
	  	this.id=id;
	  	this.login=login;
	    this.name = name;
	    this.salary = salary;
	    this.startDate=startDate;
	  }

	  public String getId() {
	    return this.id;
	  }
	  public String getLogin() {
		    return this.login;
		  }
	  public String getName() {
	    return this.name;
	  }

	  public Double getSalary() {
	    return this.salary;
	  }
	  public LocalDate getStartDate() {
		    return this.startDate;
		  }
	  public void setId(String id) {
	    this.id = id;
	  }
	  public void setLogin(String login) {
		    this.login = login;
		  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public void setSalary(Double salary) {
	    this.salary = salary;
	  }
	  public void setStartDate(LocalDate startDate) {
		    this.startDate = startDate;
		  }
	  @Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof User))
	      return false;
	    User user = (User) o;
	    return Objects.equals(this.id, user.id) && Objects.equals(this.login, user.login) 
	    		&& Objects.equals(this.name, user.name)
	        && Objects.equals(this.salary, user.salary) && Objects.equals(user.id, user.login);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.login,this.name, this.salary,this.startDate);
	  }

	  @Override
	  public String toString() {
	    return "User{" + "id=" + this.id + ",login='"+this.login +'\''+", name='" + this.name + '\'' + ", salary='" + this.salary + '\'' + ", startDate='"+this.startDate+'\''+'}';
	  }
}
