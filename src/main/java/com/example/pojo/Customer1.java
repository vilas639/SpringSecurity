package com.example.pojo;

import org.hibernate.annotations.Struct;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "customer1")
 @AllArgsConstructor @RequiredArgsConstructor  @Getter @Setter
public class Customer1 {
	
     @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;
	
     @JsonProperty("Email")
	@Column(name = "email")
	private String email;
	
     @JsonProperty("Pwd")
	private String pwd;
	
     @JsonProperty("Role")
	private String role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Customer1 [id=" + id + ", email=" + email + ", pwd=" + pwd + ", role=" + role + "]";
	}
	
	
	
	
	
}
