package com.example.ServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pojo.Customer1;
import com.example.repository.Customer1Repository;
import com.example.service.Customer1Service;

import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;


@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class Customer1ServiceImple implements Customer1Service ,UserDetailsService{

	
	@Autowired
	Customer1Repository custorepo;
	
	@Override
	public UserDetails findByEmail(String email){
	    // Fetch the customer by email or throw an exception if not found
	    Customer1 ct = custorepo.findByEmail(email).orElseThrow(() ->
	            new UsernameNotFoundException("Username not found"));

	    // Create a list of granted authorities using the role from Customer1
	    List<GrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority(ct.getRole()));


	    // Return a User object with email, password, and roles
	    return new User(ct.getEmail(), ct.getPwd(), roles);
	}

	@Override
	public String newcustomer(Customer1 c) {
		// TODO Auto-generated method stub
		
		String msg="";
		try
		{
			custorepo.save(c);
			
			if(c.getId()>0)
			{
				msg= "cusotmer create sucessfully";
			}
		}
		catch(Exception e)
		{
			e.getMessage();
			
			msg= "there is error"+e.getMessage();
		}
		return msg;
		
		
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 Customer1 ct = custorepo.findByEmail(username).orElseThrow(() ->
         new UsernameNotFoundException("Username not found"));

 // Create a list of granted authorities using the role from Customer1
 List<GrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority(ct.getRole()));


 // Return a User object with email, password, and roles
       return new User(ct.getEmail(), ct.getPwd(), roles);
	}

}
