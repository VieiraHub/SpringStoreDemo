package com.vieiraatelier.demostore.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vieiraatelier.demostore.domain.Customer;

public class CustomerDTO implements Serializable {
	private static final long serialVersionUID = -8074920684539817285L;
	
	private Integer id;
	
	@NotEmpty(message = "Filling is mandatory")
	@Length(min = 5, max = 100, message = "The size should be between 4 to 100 characters")
	private String name;
	
	@NotEmpty(message = "Filling is mandatory")
	@Email(message = "Invalid email")
	private String email;

	
	public CustomerDTO() {  }

	public CustomerDTO(Customer obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {  return id;  }

	public void setId(Integer id) {  this.id = id;  }

	public String getName() {  return name;  }

	public void setName(String name) {  this.name = name;  }

	public String getEmail() {  return email;  }

	public void setEmail(String email) {  this.email = email;  }
	
}
