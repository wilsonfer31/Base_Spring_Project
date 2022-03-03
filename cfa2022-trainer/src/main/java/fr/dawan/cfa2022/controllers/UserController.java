package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.UserDto;
import fr.dawan.cfa2022.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(produces = "application/json")
	public List<UserDto> getAll(){
		return userService.getAll();
	}
	
	// /api/users/{id} <= PathVariable (param dans l'URL)
	//api/users?email=xxxx&p2=AAAA (Request Param)
	
	// /api/users/{id}
	@GetMapping(value="/{id}", produces = "application/json")
	public UserDto findById(@PathVariable("id") long id){
		return userService.getById(id);
	}
	
	@PostMapping(consumes="application/json", produces = "application/json")
	public ResponseEntity<UserDto> save(@RequestBody UserDto uDto){
		UserDto result = userService.saveOrUpdate(uDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(result);
	}
	
	@PutMapping(consumes="application/json", produces = "application/json")
	public UserDto update(@RequestBody UserDto uDto){
		return userService.saveOrUpdate(uDto);
	}
	
	//suppression
	@DeleteMapping(value="/{id}") //dans PathVariable, tout param est obligatoire,
	                              //mettre required à false sinon
	public ResponseEntity<Long> delete(@PathVariable(name = "id")long id){
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(id);
	}
	
	
	//Utilisation de RequestParam (paramètre nommé optionel)
	// GET /{page}/{max}?search=xxxxx
//	@GetMapping(value="/{page}/{size}", produces = "application/json")
//	public List<UserDto> getAllByPage(
//							@PathVariable("page") int page, 
//							@PathVariable("size") int max,
//							@RequestParam(required = false, name = "search") String search){
//		return userService.getAll(page-1, max, "");
//	}
	
	//Solution 2 avec PathVariable et dupliquer les URI
	@GetMapping(value= {"/{page}/{size}", "/{page}/{size}/{search}"}, produces = "application/json")
	public List<UserDto> getAllByPage(
							@PathVariable("page") int page, 
							@PathVariable("size") int max, 
							@PathVariable(value="search", required = false) Optional<String> search){
		if(search.isPresent())
			return userService.getAll(page-1, max, search.get());
		else
			return userService.getAll(page-1, max, "");
	}
	
	
	// GET /count/{search}
	@GetMapping(value= {"/count","/count/{search}"}, produces = "application/json")
	public CountDto countBy(@PathVariable(value = "search",required = false) Optional<String> search) {
		CountDto result = null;
		if(search.isPresent())
			result = userService.count(search.get());
		else
			result = userService.count("");
		
		return result;
	}
	
	
	
	
	
}



