package tn.esprit.dari.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Ad implements Serializable {
	
	
		
		private static final long serialVersionUID = 1L;
	 
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		
		private String title;
		
		private String image;
		
		
		private String description;
		
		@Temporal(TemporalType.DATE)
		private Date date;
		
		private String location;
		
		private String seller;
		
		private String rating;
		
		private float taux_achat;
		
		private float price;
		
		
		
		@Enumerated(EnumType.STRING)
		private StatusAd statusad;
		
	
        @JsonIgnore
		@ManyToOne
		private User user;
        @JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy="ad") 
		private Set<Favorite> favorite;
        @JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy="ad") 
		private Set<Visit> visit;
        @JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy="ad") 
		private Set<Quote> quote;
        @JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy="ad") 
		private Set<Comment> comment;

		public Ad() {
			super();
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
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

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getSeller() {
			return seller;
		}


		public void setSeller(String seller) {
			this.seller = seller;
		}

		public String getRating() {
			return rating;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

		public StatusAd getStatusad() {
			return statusad;
		}

		public void setStatusad(StatusAd statusad) {
			this.statusad = statusad;
		}

	

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Set<Favorite> getFavorite() {
			return favorite;
		}

		public void setFavorite(Set<Favorite> favorite) {
			this.favorite = favorite;
		}

		public Set<Visit> getVisit() {
			return visit;
		}

		public void setVisit(Set<Visit> visit) {
			this.visit = visit;
		}

		public Set<Quote> getQuote() {
			return quote;
		}

		public void setQuote(Set<Quote> quote) {
			this.quote = quote;
		}

		public Set<Comment> getComment() {
			return comment;
		}

		public void setComment(Set<Comment> comment) {
			this.comment = comment;
		}
		
		public float getTaux_achat() {
			return taux_achat;
		}

		public void setTaux_achat(float taux_achat) {
			this.taux_achat = taux_achat;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		
}
