package com.vieiraatelier.demostore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Address implements Serializable {
	private static final long serialVersionUID = -8775344611604960965L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String street;
	private String number;
	private String complement;
	private String neighborhood;
	private String postalCode;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	
	public Address() {  }
	
	public Address(Integer id, String street, String number, String complement, String neighborhood,
			String postalCode, City city, Customer customer) {
		super();
		this.id = id;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.postalCode = postalCode;
		this.city = city;
		this.customer = customer;
	}

	public Integer getId() {  return id;  }

	public void setId(Integer id) {  this.id = id;  }

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

	public City getCity() {  return city;  }

	public void setCity(City city) {  this.city = city;  }

	public Customer getCustomer() {  return customer;  }

	public void setCustomer(Customer customer) {  this.customer = customer;  }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)  return true;
		if (obj == null)  return false;
		if (getClass() != obj.getClass())  return false;
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)  return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
