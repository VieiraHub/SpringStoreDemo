package com.vieiraatelier.demostore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vieiraatelier.demostore.domain.Address;
import com.vieiraatelier.demostore.domain.City;
import com.vieiraatelier.demostore.domain.Customer;
import com.vieiraatelier.demostore.domain.enums.CustomerType;
import com.vieiraatelier.demostore.dto.CustomerDTO;
import com.vieiraatelier.demostore.dto.CustomerNewDTO;
import com.vieiraatelier.demostore.repositories.AddressRepository;
import com.vieiraatelier.demostore.repositories.CustomerRepository;
import com.vieiraatelier.demostore.services.exceptions.DataIntegrityException;
import com.vieiraatelier.demostore.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Customer find(Integer id) {
		Optional<Customer> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Customer.class.getName()));
	}
	
	@Transactional
	public Customer insert(Customer obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepo.saveAll(obj.getAddresses());
		return obj;
	}
	
	public Customer update(Customer obj) {
		Customer newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Isn't possible to delete because there are related Orders");
		}
	}
	
	public List<Customer> findAll() {
		return repo.findAll();
	}
	
	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Customer fromDTO(CustomerDTO objDto) {
		return new Customer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}
	
	public Customer fromDTO(CustomerNewDTO objDto) {
		Customer customer = new Customer(null, objDto.getName(), objDto.getEmail(),
				objDto.getTaxPayerNumber(), CustomerType.toEnum(objDto.getType()), 
				passwordEncoder.encode(objDto.getPassword()));
		City city = new City(objDto.getCityId(), null, null);
		Address address = new Address(null, objDto.getStreet(), objDto.getNumber(), 
				objDto.getComplement(), objDto.getNeighborhood(), objDto.getPostalCode(), 
				city, customer);
		customer.getAddresses().add(address);
		customer.getPhones().add(objDto.getPhone1());
		if (objDto.getPhone2() != null) customer.getPhones().add(objDto.getPhone2());
		if (objDto.getPhone3() != null) customer.getPhones().add(objDto.getPhone3());
		
		return customer;
	}
	
	private void updateData(Customer newObj, Customer obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
