package tn.esprit.dari.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



	@Entity
	public class Cart implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			private String item ;
			
			@OneToOne(mappedBy="cart") 
			private User user;
			
			@OneToOne
			private Delivery delivery;
			
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
			public Cart() {
				super();
			}
			public User getUser() {
				return user;
			}
			public void setUser(User user) {
				this.user = user;
			}
			public Delivery getDelivery() {
				return delivery;
			}
			public void setDelivery(Delivery delivery) {
				this.delivery = delivery;
			}
			
			
			
			
			

}
