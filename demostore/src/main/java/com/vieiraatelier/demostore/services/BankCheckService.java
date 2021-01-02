package com.vieiraatelier.demostore.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.vieiraatelier.demostore.domain.PaymentWithCheck;

@Service
public class BankCheckService {

	public void fillPaymentWithCheck(PaymentWithCheck paymentWithCheck, Date instanceOfOrder) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanceOfOrder);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		paymentWithCheck.setDueDate(cal.getTime());
	}
}
