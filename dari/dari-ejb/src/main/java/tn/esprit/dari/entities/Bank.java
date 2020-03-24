package tn.esprit.dari.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

	@Entity
	public class Bank implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			private String intrest;
			
			private String label;
			
			
			private String mail;

			@OneToOne(mappedBy="bank")
			private Credit_simulation credit_simulation;

			public int getId() {
				return id;
			}


			public void setId(int id) {
				this.id = id;
			}


			public String getIntrest() {
				return intrest;
			}


			public void setIntrest(String intrest) {
				this.intrest = intrest;
			}


			public String getLabel() {
				return label;
			}


			public void setLabel(String label) {
				this.label = label;
			}


			public String getMail() {
				return mail;
			}


			public void setMail(String mail) {
				this.mail = mail;
			}


			public Bank() {
				super();
			}


			public Credit_simulation getCredit_simulation() {
				return credit_simulation;
			}


			public void setCredit_simulation(Credit_simulation credit_simulation) {
				this.credit_simulation = credit_simulation;
			}
			
			
			
			

}
