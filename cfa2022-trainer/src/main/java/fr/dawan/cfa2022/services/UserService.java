package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.dto.ChangePwdDto;
import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.LoginDto;
import fr.dawan.cfa2022.dto.LoginResponseDto;
import fr.dawan.cfa2022.dto.UserDto;

public interface UserService {

	List<UserDto> getAll();
	
	UserDto getById(long id);
	
	UserDto saveOrUpdate(UserDto uDto);

	void delete(long id);
	
	List<UserDto> getAll(int page, int max, String search);

	CountDto count(String search);

	LoginResponseDto checkLogin(LoginDto loginDto) throws Exception ;
	
	UserDto findByEmail(String email);
	
	boolean resetPassword(ChangePwdDto changePwdObj) throws Exception;


}
