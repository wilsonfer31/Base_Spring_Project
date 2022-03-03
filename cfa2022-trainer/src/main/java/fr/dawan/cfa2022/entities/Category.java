package fr.dawan.cfa2022.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@SuppressWarnings("serial")
@Entity
public class Category implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy =  "categorie")
	private List<Training> traningCategory;
	
	
	private String nomCategory;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNomCategory() {
		return nomCategory;
	}


	public void setNomCategory(String nomCategory) {
		this.nomCategory = nomCategory;
	}
	
	
	
	
}
