package fr.dawan.cfa2022.services;

import fr.dawan.cfa2022.dto.UserDto;

public interface EmailService {

	void sendEmailForResetPwd(UserDto uDto)  throws Exception;

}