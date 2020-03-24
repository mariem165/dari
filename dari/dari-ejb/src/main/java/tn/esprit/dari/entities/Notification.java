package tn.esprit.dari.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


	@Entity
	public class Notification implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			private String type;
			@Temporal(TemporalType.TIME)
			private Date time;
			
			@ManyToOne
			private User user;
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
			public Date getTime() {
				return time;
			}
			public void setTime(Date time) {
				this.time = time;
			}
			public Notification() {
				super();
			}
			public User getUser() {
				return user;
			}
			public void setUser(User user) {
				this.user = user;
			}
			
			
			
	}
