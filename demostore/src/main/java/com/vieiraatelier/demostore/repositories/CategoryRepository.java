package com.vieiraatelier.demostore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vieiraatelier.demostore.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
