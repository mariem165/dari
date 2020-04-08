package tn.esprit.dari.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonFormat;

	@Entity
	public class Contract implements Serializable {

	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		
	
	
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")

		private Date start_Date;
	
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
		private Date end_Date;
	
	
		@Enumerated(EnumType.STRING)
		private ContractModelType contract_model_type;
	
		@ManyToOne
		private User user;

		public ContractModelType getContract_model_type() {
			return contract_model_type;
			}

		public void setContract_model_type(ContractModelType contract_model_type) {
			this.contract_model_type = contract_model_type;
			}

		public User getUser() {
			return user;
			}

		public void setUser(User user) {
			this.user = user;
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
    	
	
	
	
}	
