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
	public class Quote  implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
		
			private String description;
			
			@Temporal(TemporalType.DATE)
			private Date date;
			
			@ManyToOne
			private Ad ad;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public Date getDate() {
				return date;
			}

			public void setDate(Date date) {
				this.date = date;
			}

			public Quote() {
				super();
			}

			public Ad getAd() {
				return ad;
			}

			public void setAd(Ad ad) {
				this.ad = ad;
			}
			
			

}
