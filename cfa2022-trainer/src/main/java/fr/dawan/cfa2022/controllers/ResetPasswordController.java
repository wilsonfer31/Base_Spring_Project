
package fr.dawan.cfa2022.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.ChangePwdDto;
import fr.dawan.cfa2022.dto.ResetPasswordDto;
import fr.dawan.cfa2022.dto.UserDto;
import fr.dawan.cfa2022.services.EmailService;
import fr.dawan.cfa2022.services.UserService;
import fr.dawan.cfa2022.tools.TokenSaver;

@RestController
public class ResetPasswordController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	//Mot-de passe oublié, je reçois un email et je vérifie puis j'envoi un lien 
	//par email pour réinitialiser le mot de passe
	@PostMapping(value="/reset-password", consumes="application/json",produces="application/json")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetObj) throws Exception {
		
		UserDto uDto = userService.findByEmail(resetObj.getEmail());

		if(uDto==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		else {
			emailService.sendEmailForResetPwd(uDto);
			return ResponseEntity.status(HttpStatus.OK).body("Email sent for update");
		}
	}
		
		@PostMapping(value = "/change-password", consumes = "application/json", produces = "application/json")
		public ResponseEntity<String> changePassword(@RequestBody ChangePwdDto changePwdObj) throws Exception {

			boolean isReferencedToken = TokenSaver.tokensByEmail.containsValue(changePwdObj.getToken());

			if (isReferencedToken) {
				// modifier le mot de passe
				boolean resetStatus = userService.resetPassword(changePwdObj);
				if (resetStatus)
					return ResponseEntity.status(HttpStatus.OK).body("Password updated !");
				else
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Operation not acceptable !");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid token");
			}

		}
	
}