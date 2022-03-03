package fr.dawan.cfa2022.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("FROM Product p WHERE p.name LIKE :search OR p.description LIKE :search")
    List<Product> findBy(@Param("search") String name);

}
