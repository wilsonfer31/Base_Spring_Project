package fr.dawan.cfa2022.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.ChangePwdDto;
import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.dto.LoginDto;
import fr.dawan.cfa2022.dto.LoginResponseDto;
import fr.dawan.cfa2022.dto.UserDto;
import fr.dawan.cfa2022.entities.User;
import fr.dawan.cfa2022.repositories.UserRepository;
import fr.dawan.cfa2022.tools.HashTools;
import fr.dawan.cfa2022.tools.JwtTokenUtil;
import fr.dawan.cfa2022.tools.TokenSaver;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	public List<UserDto> getAll() {
		List<User> users = userRepository.findAll();
		List<UserDto> result = new ArrayList<UserDto>();
		for (User u : users) {
			result.add(DtoTools.convert(u, UserDto.class));
		}
		return result;
	}

	@Override
	public UserDto getById(long id) {
		Optional<User> u = userRepository.findById(id);
		if (u.isPresent())
			return DtoTools.convert(u.get(), UserDto.class);

		return null;
	}

	@Override
	public UserDto saveOrUpdate(UserDto uDto) {
		User u = DtoTools.convert(uDto, User.class);
		try {
			if (u.getId() == 0) { // insertion
				u.setCreationDate(LocalDateTime.now());
				u.setPassword(HashTools.hashSHA512(u.getPassword()));
			} else { // modif
				UserDto userInDb = getById(u.getId());
				if(!userInDb.getPassword().contentEquals(u.getPassword())) {
					u.setPassword(HashTools.hashSHA512(u.getPassword()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		u = userRepository.saveAndFlush(u);
		return DtoTools.convert(u, UserDto.class);
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<UserDto> getAll(int page, int max, String search) {
		//on requête la bdd
		List<User> users = userRepository.findAllByFirstNameContainingOrLastNameContainingOrEmailContaining(
				search, search, search, PageRequest.of(page, max)).get().collect(Collectors.toList());
		
		//on transforme le résultat en liste de DTO
		List<UserDto> result = new ArrayList<UserDto>();
		for (User u : users) {
			result.add(DtoTools.convert(u, UserDto.class));
		}
		return result;
	}

	@Override
	public CountDto count(String search) {
		long result = userRepository.countByFirstNameContainingOrLastNameContainingOrEmailContaining(search, search, search);
		CountDto d = new CountDto();
		d.setNb(result);
		return d;
	}

	@Override
	public LoginResponseDto checkLogin(LoginDto loginDto) throws Exception {
		User u = userRepository.findByEmail(loginDto.getEmail());
		if(u!=null 
				&& u.getPassword().equals(HashTools.hashSHA512(loginDto.getPassword()))
				&& u.isActive()
		) {
			LoginResponseDto result = DtoTools.convert(u, LoginResponseDto.class);
			//generate JWT TOKEN
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("user_id", u.getId());
			claims.put("user_fullName", u.getFirstName() + " " + u.getLastName());
			claims.put("user_role", u.getRole().toString());
			
			String token = jwtTokenUtil.doGenerateToken(claims , loginDto.getEmail());
			TokenSaver.tokensByEmail.put(u.getEmail(), token);
			//générer le token
			//le sauvegarder côté service pour pouvoir le vérifier lors des prochaines requêtes
			result.setToken(token);
			return result;
		}else
			throw new Exception("Error : invalid credentials !");
	}

	@Override
	public UserDto findByEmail(String email) {
		User u = userRepository.findByEmail(email);
		return DtoTools.convert(u, UserDto.class);
	}


	@Override
	public boolean resetPassword(ChangePwdDto changePwdObj) throws Exception {
		boolean expired = jwtTokenUtil.isTokenExpired(changePwdObj.getToken());
		if (expired)
			throw new Exception("Error : Expired token, ask for reset again !");

		String newPassword = HashTools.hashSHA512(changePwdObj.getPassword());
		
	
		String email = jwtTokenUtil.getUsernameFromToken(changePwdObj.getToken());
		User u = userRepository.findByEmail(email);
		
		if(u!=null) {
			String currentPwd = u.getPassword();
			
			if(newPassword.equals(currentPwd))
				throw new Exception("Error : updating with the same old password !");
			
			u.setPassword(newPassword);
			userRepository.saveAndFlush(u);
			return true;
		}
		return false;
	}
	

}

















