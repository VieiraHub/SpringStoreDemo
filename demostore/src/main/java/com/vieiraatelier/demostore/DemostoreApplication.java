package com.vieiraatelier.demostore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vieiraatelier.demostore.domain.Category;
import com.vieiraatelier.demostore.domain.City;
import com.vieiraatelier.demostore.domain.Product;
import com.vieiraatelier.demostore.domain.State;
import com.vieiraatelier.demostore.repositories.CategoryRepository;
import com.vieiraatelier.demostore.repositories.CityRepository;
import com.vieiraatelier.demostore.repositories.ProductRepository;
import com.vieiraatelier.demostore.repositories.StateRepository;

@SpringBootApplication
public class DemostoreApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Autowired
	private ProductRepository productRepository; 
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(DemostoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.0);
		Product p2 = new Product(null, "Printer", 800.0);
		Product p3 = new Product(null, "Mouse", 80.0);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State s1 = new State(null, "California");
		State s2 = new State(null, "Texas");
		
		City c1 = new City(null, "Los Angeles", s1);
		City c2 = new City(null, "Houston", s2);
		City c3 = new City(null, "Dallas", s2);
		
		s1.getCities().addAll(Arrays.asList(c1));
		s2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(s1, s2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
	}

}
