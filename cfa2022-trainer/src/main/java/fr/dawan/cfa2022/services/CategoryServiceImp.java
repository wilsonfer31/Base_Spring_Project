package fr.dawan.cfa2022.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.entities.Category;
import fr.dawan.cfa2022.entities.Product;
import fr.dawan.cfa2022.repositories.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category save(Category c) {
		 categoryRepository.save(c);
		 return c;
	}

	@Override
	public Category Update(Category c) {
		Category categoryInDB = getById(c.getId());
		categoryInDB.setNomCategory(c.getNomCategory());
        
		categoryRepository.saveAndFlush(categoryInDB);
        return categoryInDB;
	}

	@Override
	public Category getById(long id) {
		Optional<Category> c = categoryRepository.findById(id);
		return c.get();
	}

}
