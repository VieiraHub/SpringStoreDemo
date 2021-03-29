package com.vieiraatelier.demostore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vieiraatelier.demostore.domain.State;
import com.vieiraatelier.demostore.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repo;
	
	public List<State> findAll(){
		return repo.findAllByOrderByName();
	}
}
