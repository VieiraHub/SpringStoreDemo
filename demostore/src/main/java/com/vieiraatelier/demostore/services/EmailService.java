package com.vieiraatelier.demostore.services;

import org.springframework.mail.SimpleMailMessage;

import com.vieiraatelier.demostore.domain.Order;

public interface EmailService {
	void sendOrderConfirmationEmail(Order obj);
	void sendEmail(SimpleMailMessage msg);
}
