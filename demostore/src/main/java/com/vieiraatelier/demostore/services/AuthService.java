package com.vieiraatelier.demostore.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vieiraatelier.demostore.domain.Customer;
import com.vieiraatelier.demostore.repositories.CustomerRepository;
import com.vieiraatelier.demostore.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		Customer customer = customerRepository.findByEmail(email);
		if(customer == null) {
			throw new ObjectNotFoundException("Email not found.");
		}
		
		String newPass = newPassword();
		customer.setPassword(passwordEncoder.encode(newPass));
		
		customerRepository.save(customer);
		emailService.sendNewPasswordEmail(customer, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if(opt == 0) {
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) {
			return (char) (random.nextInt(26) + 65);
		} else {
			return (char) (random.nextInt(26) + 97);
		}
	}
}
