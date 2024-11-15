package com.example.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pojo.Customer1;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer1,Long> {

    Optional<Customer1> findByEmail(String email);

}
