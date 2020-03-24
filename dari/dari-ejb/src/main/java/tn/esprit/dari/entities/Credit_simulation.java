package tn.esprit.dari.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


	@Entity
	public class Credit_simulation implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			private String document;
			
			@OneToOne(mappedBy="credit_simulation") 
			private User user;
			
			@OneToOne
			private Bank bank;
			

			public Credit_simulation() {
				super();
			}

			public String getDocument() {
				return document;
			}

			public void setDocument(String document) {
				this.document = document;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public User getUser() {
				return user;
			}

			public void setUser(User user) {
				this.user = user;
			}

			public Bank getBank() {
				return bank;
			}

			public void setBank(Bank bank) {
				this.bank = bank;
			}
			
			
			
			
}
