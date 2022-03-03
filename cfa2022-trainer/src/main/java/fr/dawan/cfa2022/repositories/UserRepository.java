package fr.dawan.cfa2022.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import fr.dawan.cfa2022.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("FROM User u WHERE u.email= :email") //si SQL native, mettre nativeQuery=true
	User findByEmail(@Param("email") String email);
	
	@Query("FROM User u WHERE u.firstName LIKE :search OR u.lastName LIKE :search")
	List<User> findBy(@Param("search") String firstOrLastName);
	
	Page<User> findAllByFirstNameContainingOrLastNameContainingOrEmailContaining(
			String firstName, String lastName, String email, Pageable pageable);
	
	long countByFirstNameContainingOrLastNameContainingOrEmailContaining(
			String firstName, String lastName, String email);
}
