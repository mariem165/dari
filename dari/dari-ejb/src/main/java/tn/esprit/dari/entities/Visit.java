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
	public class Visit  implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			private String type;
			
			private String location;
			
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


			public String getType() {
				return type;
			}


			public void setType(String type) {
				this.type = type;
			}


			public String getLocation() {
				return location;
			}


			public void setLocation(String location) {
				this.location = location;
			}


			public Date getDate() {
				return date;
			}


			public void setDate(Date date) {
				this.date = date;
			}


			public Visit() {
				super();
			}


			public Ad getAd() {
				return ad;
			}


			public void setAd(Ad ad) {
				this.ad = ad;
			}
			
			
			

}
