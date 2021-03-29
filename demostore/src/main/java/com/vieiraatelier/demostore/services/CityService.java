package com.vieiraatelier.demostore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vieiraatelier.demostore.domain.City;
import com.vieiraatelier.demostore.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;
	
	public List<City> findByState(Integer stateId){
		return repo.findCities(stateId);
	}
}
