package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.SecurityConfig;
import com.example.pojo.Customer1;
import com.example.service.Customer1Service;

import lombok.CustomLog;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	Customer1Service custservice;
	
	@PostMapping("new")
	 ResponseEntity<String> newregister(@RequestBody Customer1 c)
	{
		System.out.println("customer n"+c.toString());
		SecurityConfig s= new  SecurityConfig();
		String p=s.passwordEncoder().encode(c.getPwd());
		System.out.println(p);
		c.setPwd(p);
		System.out.println("customer after hash"+c.toString());
	String s1=	custservice.newcustomer(c);
		
	return new ResponseEntity<>(s1, HttpStatus.OK);
		
	}
}
