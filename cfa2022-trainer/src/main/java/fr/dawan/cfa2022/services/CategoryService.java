package fr.dawan.cfa2022.services;

import java.util.List;

import fr.dawan.cfa2022.entities.Category;
import fr.dawan.cfa2022.entities.Product;


public interface CategoryService {
	List<Category> getAll();

	Category save(Category c);
	
	Category Update(Category c);
	
	Category getById(long id);
}
