package fr.dawan.cfa2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
