package com.vieiraatelier.demostore.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 6463135355776748131L;
	
	@NotEmpty(message = "Filling is mandatory")
	@Email(message = "Invalid email")
	private String email;

	public EmailDTO() {  }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }
}
