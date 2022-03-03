package fr.dawan.cfa2022.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.print.attribute.standard.DateTimeAtCreation;


@SuppressWarnings("serial")
@Entity
public class Product implements Serializable{
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long  serialNumber ;
	private String name;
	private String description;
	private double price;
	private LocalDateTime dateDeCreation;
	@Version 
	private int version;
	public long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDateTime getDateDeCreation() {
		return dateDeCreation;
	}
	public void setDateDeCreation(LocalDateTime dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	
	
	
}
