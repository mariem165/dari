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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



	@Entity
	public class User implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			private String first_name;
			
			private String last_name;
			private String email;
			private String password;
			private String confirmationToken;
            private Date createdAt;
            private String address;
			private String Ntelephone ;
			private String profileImage;
			
			@Enumerated(EnumType.STRING)
			private UserType usertype;
			
			@Enumerated(EnumType.STRING)
			private AccountState accountState;
		
			@OneToOne
			private Complaint complaint;
			
			@OneToOne
			private Cart cart;
			
			@OneToOne
			private Credit_simulation credit_simulation;
			@JsonIgnore
			@OneToMany(cascade = CascadeType.ALL, mappedBy="user") 
			private Set<Subscription> subscription;
			
			@JsonIgnore
			@OneToMany(cascade = CascadeType.ALL, mappedBy="user") 
			private Set<Contract> contracts;
			
			@JsonIgnore
			@OneToMany(cascade = CascadeType.ALL, mappedBy="user") 
			private Set<Notification> notification;
			
			@JsonIgnore
			@OneToMany(cascade = CascadeType.ALL, mappedBy="user") 
			private Set<History> history;
			
			@JsonIgnore
			@OneToMany(cascade = CascadeType.ALL, mappedBy="user") 
			private Set<Promotion> promotion;
			
			@JsonIgnore
			@OneToMany(cascade = CascadeType.ALL, mappedBy="user") 
			private Set<Ad> ad;
			
			@JsonIgnore
			@OneToMany(cascade = CascadeType.ALL, mappedBy="user") 
			private Set<Comment> comment;
			
			@JsonIgnore
			@OneToMany(cascade = CascadeType.ALL, mappedBy="user") 
			private Set<Favorite> favorite;
			
			
			public int getId() {
				return id;
			}


			public void setId(int id) {
				this.id = id;
			}


			public String getFirst_name() {
				return first_name;
			}


			public void setFirst_name(String first_name) {
				this.first_name = first_name;
			}


			public String getLast_name() {
				return last_name;
			}


			public void setLast_name(String last_name) {
				this.last_name = last_name;
			}





			public String getAddress() {
				return address;
			}


			public void setAddress(String address) {
				this.address = address;
			}



			public String getNtelephone() {
				return Ntelephone;
			}


			public void setNtelephone(String ntelephone) {
				Ntelephone = ntelephone;
			}


			public UserType getUsertype() {
				return usertype;
			}


			public void setUsertype(UserType usertype) {
				this.usertype = usertype;
			}

			public Complaint getComplaint() {
				return complaint;
			}


			public void setComplaint(Complaint complaint) {
				this.complaint = complaint;
			}


			public Cart getCart() {
				return cart;
			}


			public void setCart(Cart cart) {
				this.cart = cart;
			}


			public Credit_simulation getCredit_simulation() {
				return credit_simulation;
			}


			public void setCredit_simulation(Credit_simulation credit_simulation) {
				this.credit_simulation = credit_simulation;
			}


			public Set<Subscription> getSubscription() {
				return subscription;
			}


			public void setSubscription(Set<Subscription> subscription) {
				this.subscription = subscription;
			}


			public Set<Notification> getNotification() {
				return notification;
			}


			public void setNotification(Set<Notification> notification) {
				this.notification = notification;
			}


			public Set<History> getHistory() {
				return history;
			}


			public void setHistory(Set<History> history) {
				this.history = history;
			}


			public Set<Promotion> getPromotion() {
				return promotion;
			}


			public void setPromotion(Set<Promotion> promotion) {
				this.promotion = promotion;
			}


			public Set<Ad> getAd() {
				return ad;
			}


			public void setAd(Set<Ad> ad) {
				this.ad = ad;
			}


			public Set<Comment> getComment() {
				return comment;
			}


			public void setComment(Set<Comment> comment) {
				this.comment = comment;
			}


			public Set<Favorite> getFavorite() {
				return favorite;
			}


			public void setFavorite(Set<Favorite> favorite) {
				this.favorite = favorite;
			}


			public String getEmail() {
				return email;
			}


			public void setEmail(String email) {
				this.email = email;
			}


			public String getPassword() {
				return password;
			}


			public void setPassword(String password) {
				this.password = password;
			}


			public String getConfirmationToken() {
				return confirmationToken;
			}


			public void setConfirmationToken(String confirmationToken) {
				this.confirmationToken = confirmationToken;
			}


			public Date getCreatedAt() {
				return createdAt;
			}


			public void setCreatedAt(Date createdAt) {
				this.createdAt = createdAt;
			}


			public User() {
				super();
			}


			


			public User(int id, String first_name, String last_name, String ntelephone, String email, String password) {
				super();
				this.id = id;
				this.first_name = first_name;
				this.last_name = last_name;
				this.email = email;
				this.password = password;
				Ntelephone = ntelephone;
			}


			public AccountState getAccountState() {
				return accountState;
			}


			public void setAccountState(AccountState accountState) {
				this.accountState = accountState;
			}


			public User(int id, String confirmationToken) {
				super();
				this.id = id;
				this.confirmationToken = confirmationToken;
			}
			
			public User(int id, String first_name, String last_name, String ntelephone, String email, String password,
					String confirmationToken, String address, Date createdAt,
					AccountState accountState) {
				super();
				this.id = id;
				this.first_name = first_name;
				this.last_name = last_name;
				this.email = email;
				this.password = password;
				this.confirmationToken = confirmationToken;
				this.createdAt = createdAt;
				this.address = address;
				this.Ntelephone = ntelephone;
				this.accountState = accountState;
			}


			public User(String email, String password) {
				super();
				this.email = email;
				this.password = password;
			}


			public User(String first_name, String last_name, String email, String password, String address,
					String ntelephone , UserType usertype , String profileImage ) {
				super();
				this.first_name = first_name;
				this.last_name = last_name;
				this.email = email;
				this.password = password;
				this.address = address;
				Ntelephone = ntelephone;
				this.usertype = usertype;
				this.profileImage=profileImage;
			}


			public User(int id, String first_name, String last_name, String email, String password, String address,
					String ntelephone) {
				super();
				this.id = id;
				this.first_name = first_name;
				this.last_name = last_name;
				this.email = email;
				this.password = password;
				this.address = address;
				Ntelephone = ntelephone;
			}


			public String getProfileImage() {
				return profileImage;
			}


			public void setProfileImage(String profileImage) {
				this.profileImage = profileImage;
			}


			public Set<Contract> getContracts() {
				return contracts;
			}


			public void setContracts(Set<Contract> contracts) {
				this.contracts = contracts;
			}


			public static long getSerialversionuid() {
				return serialVersionUID;
			}
			
			
			
			
			
			
			
			


}
