package tn.esprit.dari.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class PurchaseAd implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String image;
	
	
	private String description;
	
	private String location;
	
	private int room;
	private double lat;
    private double lng;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	private float area;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private float price;
	
	
	
	@Enumerated(EnumType.STRING)
	private StatusAd statusad;
	
	
	 @JsonIgnore
		@ManyToOne
		private User user;
	 



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public StatusAd getStatusad() {
		return statusad;
	}


	public void setStatusad(StatusAd statusad) {
		this.statusad = statusad;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	

	public int getRoom() {
		return room;
	}


	public void setRoom(int room) {
		this.room = room;
	}


	public float getArea() {
		return area;
	}


	public void setArea(float area) {
		this.area = area;
	}


	public PurchaseAd(int id, String title, String image, String description, Date date, float price, StatusAd statusad,
			User user) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.description = description;
		this.date = date;
		this.price = price;
		this.statusad = statusad;
		this.user = user;
	}

	public PurchaseAd(int id, String title, String image, String description, Date date, float price, StatusAd statusad
			,String location
			) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.description = description;
		this.date = date;
		this.price = price;
		this.statusad = statusad;
		this.location = location;
		
	}
	
	

	public PurchaseAd(String title, String image, String description, String location, Date date, float price , int room , float area, double lat,double lng) {
		super();
		this.title = title;
		this.image = image;
		this.description = description;
		this.location = location;
		this.date = date;
		this.price = price;
		this.room = room;
		this.area = area;
		this.lat =lat;
		this.lng=lng;
		
	}

	public PurchaseAd(int id,String title, String image, String description, String location, Date date, float price) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.description = description;
		this.location = location;
		this.date = date;
		this.price = price;
	}


	public PurchaseAd() {
		super();
	}
	 
	 
	 

}
