package tn.esprit.dari.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


	@Entity
	public class Complaint implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			@Enumerated(EnumType.STRING)
			private StatusType status;
			
			@OneToOne(mappedBy="complaint") 
			private User user;
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public StatusType getStatus() {
				return status;
			}
			public void setStatus(StatusType status) {
				this.status = status;
			}
			public Complaint() {
				super();
			}
			public User getUser() {
				return user;
			}
			public void setUser(User user) {
				this.user = user;
			}
			
			
			
			
			
}
