package tn.esprit.dari.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


	@Entity
	public class Subscription implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			//private String subscribed_user;
			
			private String insurence;
			


			@ManyToOne
			private User user;
		
			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}
			

			public String getInsurence() {
				return insurence;
			}

			public void setInsurence(String insurence) {
				this.insurence = insurence;
			}
			

			public Subscription() {
				super();
			}

			public User getUser() {
				return user;
			}

			public void setUser(User user) {
				this.user = user;
			}
			
			
			
			
			

}
