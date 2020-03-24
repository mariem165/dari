package tn.esprit.dari.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

	@Entity
	public class Delivery implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			private String item;
			
			private String status;
			
		
			private String location;
			
			@Temporal(TemporalType.DATE)
			private Date date;
			
			@OneToOne(mappedBy="delivery") 
			private Cart cart;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getItem() {
				return item;
			}

			public void setItem(String item) {
				this.item = item;
			}

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
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

			public Delivery() {
				super();
			}

			public Cart getCart() {
				return cart;
			}

			public void setCart(Cart cart) {
				this.cart = cart;
			}
			
			
			
			
			

}
