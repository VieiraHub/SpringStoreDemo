package com.vieiraatelier.demostore.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vieiraatelier.demostore.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = -8113160789435106024L;
	
	private Integer installmentsNumber;
	
	
	public PaymentWithCard() {  }

	public PaymentWithCard(Integer id, PaymentStatus status, Order order, Integer installmentsNumber) {
		super(id, status, order);
		this.installmentsNumber = installmentsNumber; 
	}

	public Integer getInstallmentsNumber() {  return installmentsNumber;  }

	public void setInstallmentsNumber(Integer installmentsNumber) {  
		this.installmentsNumber = installmentsNumber;
	}	
}
