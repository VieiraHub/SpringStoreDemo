package com.vieiraatelier.demostore.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vieiraatelier.demostore.domain.Category;


public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 6057886035591715681L;
	
	private Integer id;
	
	@NotEmpty(message = "Filling is mandatory")
	@Length(min = 4, max = 25, message = "The size should be between 4 to 25 characters")
	private String name;
	
	public CategoryDTO() { }
	
	public CategoryDTO(Category obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

	public Integer getId() {  return id;  }

	public void setId(Integer id) {  this.id = id;  }

	public String getName() {  return name;  }

	public void setName(String name) {  this.name = name;  }
}
