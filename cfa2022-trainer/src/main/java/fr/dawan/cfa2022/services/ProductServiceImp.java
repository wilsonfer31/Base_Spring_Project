package fr.dawan.cfa2022.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.cfa2022.dto.CountDto;
import fr.dawan.cfa2022.dto.DtoTools;
import fr.dawan.cfa2022.dto.UserDto;
import fr.dawan.cfa2022.entities.Product;
import fr.dawan.cfa2022.entities.User;
import fr.dawan.cfa2022.repositories.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getById(long serialNumber) {
		Optional<Product> p = productRepository.findById(serialNumber);
		return p.get();
	}

	@Override
	public Product save(Product p) {
		 LocalDateTime dateTime = LocalDateTime.now();
		 p.setDateDeCreation(dateTime);
		productRepository.save(p);
		return p;
	}

	@Override
	public Product Update(Product p) {
	    LocalDateTime dateTime = LocalDateTime.now();
        Product productInDB = getById(p.getSerialNumber());
        productInDB.setName(p.getName());
        productInDB.setDescription(p.getDescription());
        productInDB.setPrice(p.getPrice());
        productInDB.setDateDeCreation(dateTime);
        
        productRepository.saveAndFlush(productInDB);
        return productInDB;
	}

	@Override
	public void delete(long id) {
		Product p1 = getById(id);
		productRepository.delete(p1);
		
	}

	@Override
	public List<Product> getAll(String search) {
		List<Product> users = productRepository.findBy("%"+search+"%");
		
		return users;
	}

	
	
}
