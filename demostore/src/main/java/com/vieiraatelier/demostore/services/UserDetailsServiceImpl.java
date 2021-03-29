package com.vieiraatelier.demostore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vieiraatelier.demostore.domain.Customer;
import com.vieiraatelier.demostore.repositories.CustomerRepository;
import com.vieiraatelier.demostore.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Customer customer = repo.findByEmail(email);
		if (customer == null)
			throw new UsernameNotFoundException(email);

		return new UserSpringSecurity(customer.getId(), customer.getEmail(), customer.getPassword(),
				customer.getProfiles());
	}
}
