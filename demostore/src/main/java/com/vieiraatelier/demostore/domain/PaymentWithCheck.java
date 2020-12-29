package com.vieiraatelier.demostore.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vieiraatelier.demostore.domain.enums.PaymentStatus;

@Entity
public class PaymentWithCheck extends Payment {
	private static final long serialVersionUID = -5236101365226153504L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dueDate;
	private Date payDate;
	
	
	public PaymentWithCheck() {  }

	public PaymentWithCheck(Integer id, PaymentStatus status, Order order, Date dueDate, Date payDate) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.payDate = payDate;
	}

	public Date getDueDate() {  return dueDate;  }

	public void setDueDate(Date dueDate) {  this.dueDate = dueDate;  }

	public Date getPayDate() {  return payDate;  }

	public void setPayDate(Date payDate) {  this.payDate = payDate;  }
	
}
