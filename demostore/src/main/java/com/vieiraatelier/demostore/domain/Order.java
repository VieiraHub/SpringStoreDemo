package com.vieiraatelier.demostore.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order implements Serializable {
	private static final long serialVersionUID = 5188035121205154393L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date instance;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "delivery_address_id")
	private Address deliveryAddress;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	
	public Order() {  }

	public Order(Integer id, Date instance, Customer customer, Address deliveryAddress) {
		super();
		this.id = id;
		this.instance = instance;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
	}

	public Integer getId() {  return id;  }

	public void setId(Integer id) {  this.id = id;  }

	public Date getInstance() {  return instance;  }

	public void setInstance(Date instance) {  this.instance = instance;  }

	public Payment getPayment() {  return payment;  }

	public void setPayment(Payment payment) {  this.payment = payment;  }

	public Customer getCustomer() {  return customer;  }

	public void setCustomer(Customer customer) {  this.customer = customer;  }

	public Address getDeliveryAddress() {  return deliveryAddress;  }

	public void setDeliveryAddress(Address deliveryAddress) {  this.deliveryAddress = deliveryAddress;  }

	public Set<OrderItem> getItems() {  return items;  }

	public void setItems(Set<OrderItem> items) {  this.items = items;  }

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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)  return false;
		} else if (!id.equals(other.id))  
			return false;
		return true;
	}
}
