package com.vieiraatelier.demostore.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vieiraatelier.demostore.security.UserSpringSecurity;

public class UserService {

	public static UserSpringSecurity authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		}catch (Exception e) {
			return null;
		}
	}
}
