package com.vieiraatelier.demostore.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.vieiraatelier.demostore.domain.Customer;
import com.vieiraatelier.demostore.dto.CustomerDTO;
import com.vieiraatelier.demostore.repositories.CustomerRepository;
import com.vieiraatelier.demostore.resources.exceptions.FieldMessage;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CustomerRepository repo;
	
	@Override
	public void initialize(CustomerUpdate customerUpdate) {  }

	@Override
	public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		Customer customer = repo.findByEmail(objDto.getEmail());
		if(customer != null && !customer.getId().equals(uriId)) {
			list.add(new FieldMessage("Email", "Existing email"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}