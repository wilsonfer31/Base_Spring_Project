package fr.dawan.cfa2022.entities;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class MyCustomRepositoryImpl implements MyCustomRepository {
	
	@PersistenceContext
	private EntityManager em; //objet connexion récupéré depuis le conteneur Spring
	
	@Override
	public List<User> searchBy(Map<String, String[]> params){
		Query q = em.createQuery("FROM XXXXXXX");
		//.... la suite de votre traitement
		return null;
	}
}
