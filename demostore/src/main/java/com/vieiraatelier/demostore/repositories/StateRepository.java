package com.vieiraatelier.demostore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vieiraatelier.demostore.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
