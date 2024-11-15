package com.example.pojo;


public class LoginResponseDTO {
	
	String status;
	String jwtToken;
	
	
	LoginResponseDTO()
	{
		
		
	}
	
	


	public LoginResponseDTO(String status, String jwtToken) {
		super();
		this.status = status;
		this.jwtToken = jwtToken;
	}




	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getJwtToken() {
		return jwtToken;
	}


	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
}
