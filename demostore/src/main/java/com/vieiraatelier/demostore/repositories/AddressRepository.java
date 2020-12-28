package com.vieiraatelier.demostore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vieiraatelier.demostore.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
