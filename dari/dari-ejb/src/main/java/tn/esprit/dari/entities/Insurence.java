package tn.esprit.dari.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Insurence implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String type;
	
	private float prix;
	
	private String entreprise;
	
	@OneToMany(mappedBy="insurencee" , cascade= {CascadeType.REMOVE} , fetch=FetchType.EAGER)
	private List<Subscription> lstSubscriptions;
	
	
	public List<Subscription> getLstSubscriptions() {
		return lstSubscriptions;
	}

	public void setLstSubscriptions(List<Subscription> lstSubscriptions) {
		this.lstSubscriptions = lstSubscriptions;
	}

	private String description;
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	 
 
	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
