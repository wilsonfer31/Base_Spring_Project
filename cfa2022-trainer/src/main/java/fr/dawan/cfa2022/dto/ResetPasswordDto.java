package fr.dawan.cfa2022.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResetPasswordDto implements Serializable {

	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}