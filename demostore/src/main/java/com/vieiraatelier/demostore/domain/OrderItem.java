package com.vieiraatelier.demostore.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 3145107961742202987L;

	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private Double discount;
	private Integer amount;
	private Double price;
	
	
	public OrderItem() {  }

	public OrderItem(Order order, Product product, Double discount, Integer amount, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.discount = discount;
		this.amount = amount;
		this.price = price;
	}
	
	public double getSubTotal() {
		return (price - discount) * amount;
	}
	
	@JsonIgnore
	public Order getOrder() {  return id.getOrder();  }
	
	public void setOrder(Order order) {  id.setOrder(order);  }
	
	public Product getProduct() {  return id.getProduct();  }
	
	public void setProduct(Product product) {  id.setProduct(product);  }

	public OrderItemPK getId() {  return id;  }

	public void setId(OrderItemPK id) {  this.id = id;  }

	public Double getDiscount() {  return discount;  }

	public void setDiscount(Double discount) {  this.discount = discount;  }

	public Integer getAmount() {  return amount;  }

	public void setAmount(Integer amount) {  this.amount = amount;  }

	public Double getPrice() {  return price;  }

	public void setPrice(Double price) {  this.price = price;  }

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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)  return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "PT"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduct().getName());
		builder.append(", Amount: ");
		builder.append(getAmount());
		builder.append(", Unit price: ");
		builder.append(nf.format(getPrice()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	
}
