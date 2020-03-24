package tn.esprit.dari.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


	@Entity
	public class Favorite implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			private String array ;
			
			@ManyToOne
			private User user;
			
			@ManyToOne
			private Ad ad;
			
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getArray() {
				return array;
			}
			public void setArray(String array) {
				this.array = array;
			}
			public Favorite() {
				super();
			}
			public User getUser() {
				return user;
			}
			public void setUser(User user) {
				this.user = user;
			}
			public Ad getAd() {
				return ad;
			}
			public void setAd(Ad ad) {
				this.ad = ad;
			}
			
			
			

}
