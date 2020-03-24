package tn.esprit.dari.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

	@Entity
	public class Promotion implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			private String title;
			
			private String image;
			
			
			private String description;

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


			public Promotion() {
				super();
			}


			public User getUser() {
				return user;
			}


			public void setUser(User user) {
				this.user = user;
			}
			
			

}
