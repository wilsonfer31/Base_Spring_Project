package fr.dawan.cfa2022.services;

import java.util.List;


import fr.dawan.cfa2022.entities.Product;

public interface ProductService {
	List<Product> getAll();
	
	Product getById(long serialNumber);
	
	Product save(Product p);
	
	Product Update(Product p);
	
	void delete(long id);
	
	List<Product> getAll(String search);

}
