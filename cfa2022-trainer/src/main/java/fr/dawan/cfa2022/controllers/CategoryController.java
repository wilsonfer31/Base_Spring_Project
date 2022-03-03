package fr.dawan.cfa2022.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.cfa2022.entities.Category;
import fr.dawan.cfa2022.entities.Product;
import fr.dawan.cfa2022.services.CategoryService;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping(produces = "application/json")
	public List<Category> getAll(){
		return categoryService.getAll();
	}
	
	@PostMapping(consumes="application/json", produces = "application/json")
	public ResponseEntity<Category> save(@RequestBody Category c){
		Category result = categoryService.save(c);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(result);
	}
	
	@PutMapping(consumes="application/json", produces = "application/json")
	public ResponseEntity<Category> update(@RequestBody Category c){
		Category result = categoryService.Update(c);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(result);
	}
	
	@GetMapping(value="/{id}", produces = "application/json")
	public Category findById(@PathVariable("id") long id){
		return categoryService.getById(id);
	}
}
