package com.vieiraatelier.demostore.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.vieiraatelier.demostore.domain.Customer;
import com.vieiraatelier.demostore.domain.Order;

public interface EmailService {
	void sendOrderConfirmationEmail(Order obj);
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Order obj);
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Customer customer, String newPass);
}
