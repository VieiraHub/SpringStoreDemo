package com.vieiraatelier.demostore.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vieiraatelier.demostore.domain.Customer;
import com.vieiraatelier.demostore.domain.Order;
import com.vieiraatelier.demostore.domain.OrderItem;
import com.vieiraatelier.demostore.domain.PaymentWithCheck;
import com.vieiraatelier.demostore.domain.enums.PaymentStatus;
import com.vieiraatelier.demostore.repositories.OrderItemRepository;
import com.vieiraatelier.demostore.repositories.OrderRepository;
import com.vieiraatelier.demostore.repositories.PaymentRepository;
import com.vieiraatelier.demostore.security.UserSpringSecurity;
import com.vieiraatelier.demostore.services.exceptions.AuthorizationException;
import com.vieiraatelier.demostore.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	@Autowired
	private BankCheckService checkService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EmailService emailService;

	public Order find(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Order.class.getName()));
	}
	
	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstance(new Date());
		obj.setCustomer(customerService.find(obj.getCustomer().getId()));
		obj.getPayment().setStatus(PaymentStatus.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentWithCheck) {
			PaymentWithCheck paymentWithCheck = (PaymentWithCheck) obj.getPayment();
			checkService.fillPaymentWithCheck(paymentWithCheck, obj.getInstance());
		}
		obj = repo.save(obj);
		paymentRepo.save(obj.getPayment());
		for(OrderItem oi : obj.getItems()) {
			oi.setDiscount(0.0);
			oi.setProduct(productService.find(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrder(obj);
		}
		orderItemRepo.saveAll(obj.getItems());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		UserSpringSecurity user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Access denied!");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Customer customer = customerService.find(user.getId());
		return repo.findByCustomer(customer, pageRequest);
	}
}
