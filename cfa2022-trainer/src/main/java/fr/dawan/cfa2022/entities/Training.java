package fr.dawan.cfa2022.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class Training implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique  = true , nullable = true)
	private String intitule;
	
	@Column(unique  = true)
	private String codeCPF;
	
	@OneToMany(mappedBy = "formation")
	private List<Chapitre> Chapitres ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categorie_id")
	private Category categorie;
	
	@Version 
	private int version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getCodeCPF() {
		return codeCPF;
	}

	public void setCodeCPF(String codeCPF) {
		this.codeCPF = codeCPF;
	}

	public List<Chapitre> getChapitres() {
		return Chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		Chapitres = chapitres;
	}

	public Category getCategorie() {
		return categorie;
	}

	public void setCategorie(Category categorie) {
		this.categorie = categorie;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	} 
	
	
	
}
