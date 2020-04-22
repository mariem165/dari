package tn.esprit.dari.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


	@Entity
	public class History implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			@ManyToOne
			private Ad Ads;			
			@ManyToOne
			private User user;
			
			public History() {
				super();
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}



			public Ad getAds() {
				return Ads;
			}

			public void setAds(Ad ads) {
				Ads = ads;
			}

			public User getUser() {
				return user;
			}

			public void setUser(User user) {
				this.user = user;
			}
			
			
}
