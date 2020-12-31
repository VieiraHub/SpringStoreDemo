package com.vieiraatelier.demostore.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vieiraatelier.demostore.domain.Category;
import com.vieiraatelier.demostore.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
	Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest);
	
//	Maneira mais rápida usando Spring data com padrao de nomes ver documentaçao sem query pois ela sobrepoe o nome do metodo.
//	Page<Product> findDistinctByNameContainingAndCategoriesIN(String name, List<Category> categories, Pageable pageRequest);
}
