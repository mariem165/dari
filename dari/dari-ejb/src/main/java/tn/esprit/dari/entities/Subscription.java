package tn.esprit.dari.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Subscription implements Serializable {
		
		
			
		private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			private String  insurence;  
			public String getInsurence() {
				return insurence;
			}

			public void setInsurence(String insurence) {
				this.insurence = insurence;
			}

			public Date getStart_Date() {
				return start_Date;
			}

			public void setStart_Date(Date start_Date) {
				this.start_Date = start_Date;
			}

			public Date getEnd_Date() {
				return end_Date;
			}

			public void setEnd_Date(Date end_Date) {
				this.end_Date = end_Date;
			}

			@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
			private Date start_Date;
            
			@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
			private Date end_Date;
			
			
		 
			@ManyToOne 
			@JoinColumn(name="id_insurence")
			private Insurence insurencee;
			
			@ManyToOne
			private User user;
		
			
			public int getId() {
				return id;
			}

			public Insurence getInsurencee() {
				return insurencee;
			}

			public void setInsurencee(Insurence insurencee) {
				this.insurencee = insurencee;
			}

			public void setId(int id) {
				this.id = id;
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
/*
			public Insurence getInsurencee() {
				return insurencee;
			}

			public void setInsurencee(Insurence insurencee) {
				this.insurencee = insurencee;
			}
			*/
}
