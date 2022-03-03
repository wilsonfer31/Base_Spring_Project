package fr.dawan.cfa2022.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.UserDto;
import fr.dawan.cfa2022.entities.Product;
import fr.dawan.cfa2022.entities.User;
import fr.dawan.cfa2022.services.ProductService;
import fr.dawan.cfa2022.services.UserService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(produces = "application/json")
	public List<Product> getAll(){
		return productService.getAll();
	}
	
	
	@PostMapping(consumes="application/json", produces = "application/json")
	public ResponseEntity<Product> save(@RequestBody Product p){
		Product result = productService.save(p);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(result);
	}

	@GetMapping(value="/{id}", produces = "application/json")
	public Product findById(@PathVariable("id") long id){
		return productService.getById(id);
	}
	
	
	@PutMapping(consumes="application/json", produces = "application/json")
	public ResponseEntity<Product> update(@RequestBody Product p){
		Product result = productService.Update(p);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(result);
	}
	
	
	@DeleteMapping(value="/{id}") 
	public ResponseEntity<Long> delete(@PathVariable(name = "id")long id){
		productService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(id);
}

	
	@GetMapping(value= "/find/{search}", produces = "application/json")
	public  List<Product> search(@PathVariable(value = "search",required = false) String search) {

		return productService.getAll(search);
		
		
	}
	
	
}
