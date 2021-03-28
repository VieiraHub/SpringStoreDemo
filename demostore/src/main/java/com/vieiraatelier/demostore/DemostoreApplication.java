package com.vieiraatelier.demostore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemostoreApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(DemostoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
	
}
