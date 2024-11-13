package com.example.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.pojo.Customer1;

public interface Customer1Service {
	
	
 UserDetails findByEmail(String email);
 
 
  String    newcustomer(Customer1 c);

}
