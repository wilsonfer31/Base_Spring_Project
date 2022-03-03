package fr.dawan.cfa2022.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.LoginDto;
import fr.dawan.cfa2022.dto.LoginResponseDto;
import fr.dawan.cfa2022.services.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	
	 @PostMapping(value="/login", consumes="application/json", produces="application/json")
	 public LoginResponseDto checkLogin(@RequestBody LoginDto loginDto) throws Exception {
	    	//appel à la méthode du service
	        return userService.checkLogin(loginDto);
	 }
	       
}
