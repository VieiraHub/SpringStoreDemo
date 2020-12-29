package com.vieiraatelier.demostore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vieiraatelier.demostore.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
