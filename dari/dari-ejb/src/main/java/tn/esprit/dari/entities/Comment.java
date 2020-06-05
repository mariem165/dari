package tn.esprit.dari.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

	@Entity
	public class Comment implements Serializable {
		
		
			
			private static final long serialVersionUID = 1L;
		 
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			
			private String content;
			@Temporal(TemporalType.TIME)
			private Date time;
			
			@ManyToOne
			private User user;
			
			@ManyToOne
			private Ad ad;
			
			@ManyToOne
			private Meubles meubles;
			
			public Meubles getMeubles() {
				return meubles;
			}
			public void setMeubles(Meubles meubles) {
				this.meubles = meubles;
			}
			public Comment(int id, String content, Time time) {
				super();
				this.id = id;
				this.content = content;
				this.time = time;
			}
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getContent() {
				return content;
			}
			public void setContent(String content) {
				this.content = content;
			}
			public Date getTime() {
				return time;
			}
			public void setTime(Date time) {
				this.time = time;
			}
			public Comment() {
				super();
			}
			public User getUser() {
				return user;
			}
			public void setUser(User user) {
				this.user = user;
			}
			public Ad getAd() {
				return ad;
			}
			public void setAd(Ad ad) {
				this.ad = ad;
			}
			
			
			

}
