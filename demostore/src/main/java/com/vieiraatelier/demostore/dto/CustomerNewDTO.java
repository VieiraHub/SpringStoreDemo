package com.vieiraatelier.demostore.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vieiraatelier.demostore.services.validations.CustomerInsert;

@CustomerInsert
public class CustomerNewDTO implements Serializable {
	private static final long serialVersionUID = -248169673046266273L;
	
	@NotEmpty(message = "Filling is mandatory")
	@Length(min = 5, max = 100, message = "The size should be between 4 to 100 characters")
	private String name;
	
	@NotEmpty(message = "Filling is mandatory")
	@Email(message = "Invalid email")
	private String email;
	
	@NotEmpty(message = "Filling is mandatory")
	private String taxPayerNumber;
	
	private Integer type;
	
	@NotEmpty(message = "Filling is mandatory")
	private String password;
	
	@NotEmpty(message = "Filling is mandatory")
	private String street;
	
	@NotEmpty(message = "Filling is mandatory")
	private String number;
	
	private String complement;
	private String neighborhood;
	
	@NotEmpty(message = "Filling is mandatory")
	private String postalCode;
	
	@NotEmpty(message = "Filling is mandatory")
	private String phone1;
	
	private String phone2;
	private String phone3;
	
	private Integer cityId;

	public CustomerNewDTO() {  }

	public String getName() {  return name;  }

	public void setName(String name) {  this.name = name;  }

	public String getEmail() {  return email;  }

	public void setEmail(String email) {  this.email = email;  }

	public String getTaxPayerNumber() {  return taxPayerNumber;  }

	public void setTaxPayerNumber(String taxPayerNumber) {  this.taxPayerNumber = taxPayerNumber;  }

	public Integer getType() {  return type;  }

	public void setType(Integer type) {  this.type = type;  }
	
	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

	public String getStreet() {  return street;  }

	public void setStreet(String street) {  this.street = street;  }

	public String getNumber() {  return number;  }

	public void setNumber(String number) {  this.number = number;  }

	public String getComplement() {  return complement;  }

	public void setComplement(String complement) {  this.complement = complement;  }

	public String getNeighborhood() {  return neighborhood;  }

	public void setNeighborhood(String neighborhood) {  this.neighborhood = neighborhood;  }

	public String getPostalCode() {  return postalCode;  }

	public void setPostalCode(String postalCode) {  this.postalCode = postalCode;  }

	public String getPhone1() {  return phone1;  }

	public void setPhone1(String phone1) {  this.phone1 = phone1;  }

	public String getPhone2() {  return phone2;  }

	public void setPhone2(String phone2) {  this.phone2 = phone2;  }

	public String getPhone3() {  return phone3;  }

	public void setPhone3(String phone3) {  this.phone3 = phone3;  }

	public Integer getCityId() {  return cityId;  }

	public void setCityId(Integer cityId) {  this.cityId = cityId;  }

}
