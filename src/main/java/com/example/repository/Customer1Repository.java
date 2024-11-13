package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pojo.Customer1;

@Repository
public interface Customer1Repository  extends JpaRepository<Customer1, Long>{
	
	
	 Optional<Customer1>  findByEmail(String email);

}
