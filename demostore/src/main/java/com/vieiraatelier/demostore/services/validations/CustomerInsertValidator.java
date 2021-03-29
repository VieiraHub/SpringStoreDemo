package com.vieiraatelier.demostore.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vieiraatelier.demostore.domain.Customer;
import com.vieiraatelier.demostore.domain.enums.CustomerType;
import com.vieiraatelier.demostore.dto.CustomerNewDTO;
import com.vieiraatelier.demostore.repositories.CustomerRepository;
import com.vieiraatelier.demostore.resources.exceptions.FieldMessage;
import com.vieiraatelier.demostore.services.validations.utils.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerNewDTO> {
	
	@Autowired
	private CustomerRepository repo;
	
	@Override
	public void initialize(CustomerInsert customerInsert) {  }

	@Override
	public boolean isValid(CustomerNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getType().equals(CustomerType.PHYSICAL_PERSON.getCode()) && !BR.isValidCPF(objDto.getTaxPayerNumber())) {
			list.add(new FieldMessage("TaxPayerNumber", "Invalid CPF"));
		}
		
		if(objDto.getType().equals(CustomerType.LEGAL_PERSON.getCode()) && !BR.isValidCNPJ(objDto.getTaxPayerNumber())) {
			list.add(new FieldMessage("TaxPayerNumber", "Invalid CNPJ"));
		}
		
		Customer customer = repo.findByEmail(objDto.getEmail());
		if(customer != null) {
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