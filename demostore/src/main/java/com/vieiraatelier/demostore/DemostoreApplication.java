package com.vieiraatelier.demostore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vieiraatelier.demostore.domain.Category;
import com.vieiraatelier.demostore.repositories.CategoryRepository;

@SpringBootApplication
public class DemostoreApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(DemostoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
