package com.vieiraatelier.demostore.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vieiraatelier.demostore.domain.Address;
import com.vieiraatelier.demostore.domain.Category;
import com.vieiraatelier.demostore.domain.City;
import com.vieiraatelier.demostore.domain.Customer;
import com.vieiraatelier.demostore.domain.Order;
import com.vieiraatelier.demostore.domain.OrderItem;
import com.vieiraatelier.demostore.domain.Payment;
import com.vieiraatelier.demostore.domain.PaymentWithCard;
import com.vieiraatelier.demostore.domain.PaymentWithCheck;
import com.vieiraatelier.demostore.domain.Product;
import com.vieiraatelier.demostore.domain.State;
import com.vieiraatelier.demostore.domain.enums.CustomerType;
import com.vieiraatelier.demostore.domain.enums.PaymentStatus;
import com.vieiraatelier.demostore.repositories.AddressRepository;
import com.vieiraatelier.demostore.repositories.CategoryRepository;
import com.vieiraatelier.demostore.repositories.CityRepository;
import com.vieiraatelier.demostore.repositories.CustomerRepository;
import com.vieiraatelier.demostore.repositories.OrderItemRepository;
import com.vieiraatelier.demostore.repositories.OrderRepository;
import com.vieiraatelier.demostore.repositories.PaymentRepository;
import com.vieiraatelier.demostore.repositories.ProductRepository;
import com.vieiraatelier.demostore.repositories.StateRepository;

@Service
public class DBService {

	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Autowired
	private ProductRepository productRepository; 
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository; 
	
	@Autowired
	private CustomerRepository customerRepository; 
	
	@Autowired
	private AddressRepository addressRepository; 
	
	@Autowired
	private OrderRepository orderRepository; 
	
	@Autowired
	private PaymentRepository paymentRepository; 
	
	@Autowired
	private OrderItemRepository orderItemRepository; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void instantiateTestDatabase() throws ParseException {
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Home");
		Category cat4 = new Category(null, "Electronics");
		Category cat5 = new Category(null, "Gardening");
		Category cat6 = new Category(null, "Decoration");
		Category cat7 = new Category(null, "Perfumery");
		
		Product p1 = new Product(null, "Computer", 2000.0);
		Product p2 = new Product(null, "Printer", 800.0);
		Product p3 = new Product(null, "Mouse", 80.0);
		Product p4 = new Product(null, "Office desk", 300.0);
		Product p5 = new Product(null, "Towel", 50.0);
		Product p6 = new Product(null, "Quilt", 200.0);
		Product p7 = new Product(null, "TV true color", 1200.0);
		Product p8 = new Product(null, "Brush cutter", 800.0);
		Product p9 = new Product(null, "Bedside Lamp", 100.0);
		Product p10 = new Product(null, "Pendant", 180.0);
		Product p11 = new Product(null, "Shampoo", 90.0);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		State s1 = new State(null, "California");
		State s2 = new State(null, "Texas");
		
		City c1 = new City(null, "Los Angeles", s1);
		City c2 = new City(null, "Houston", s2);
		City c3 = new City(null, "Dallas", s2);
		
		s1.getCities().addAll(Arrays.asList(c1));
		s2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(s1, s2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Customer cust1 = new Customer(null, "Bruno Vieira", "vieiraonwork@gmail.com", "3652146980", 
				CustomerType.PHYSICAL_PERSON, passwordEncoder.encode("123"));
		cust1.getPhones().addAll(Arrays.asList("918463650","910254783"));
		
		Address add1 = new Address(null, "S Spring", "634", "Apt 500", "Long Beach", "90014", c1, cust1);
		Address add2 = new Address(null, "Mockingbird Ln", "708", "A", "Pasadena", "77502", c2, cust1);
		cust1.getAddresses().addAll(Arrays.asList(add1, add2));
		
		customerRepository.saveAll(Arrays.asList(cust1));
		addressRepository.saveAll(Arrays.asList(add1, add2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order ord1 = new Order(null, sdf.parse("30/09/2020 10:32"), cust1, add1);
		Order ord2 = new Order(null, sdf.parse("10/10/2020 19:35"), cust1, add2);
		
		Payment pay1 = new PaymentWithCard(null, PaymentStatus.CLOSED, ord1, 6);
		ord1.setPayment(pay1);
		Payment pay2 = new PaymentWithCheck(null, PaymentStatus.PENDING, ord2, sdf.parse("20/10/2020 00:00"), null);
		ord2.setPayment(pay2);
		
		cust1.getOrders().addAll(Arrays.asList(ord1, ord2));
		
		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		OrderItem oi1 = new OrderItem(ord1, p1, 0.00, 1, 2000.00);
		OrderItem oi2 = new OrderItem(ord1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(ord2, p2, 100.00, 1, 800.00);
		
		ord1.getItems().addAll(Arrays.asList(oi1, oi2));
		ord2.getItems().addAll(Arrays.asList(oi3));
		
		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
}
