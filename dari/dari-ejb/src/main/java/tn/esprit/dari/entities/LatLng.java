package tn.esprit.dari.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LatLng implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
		private double lat;
	    private double lng;

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLng() {
			return lng;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}

		

		public LatLng() {
			super();
		}

		public LatLng(double lat, double lng) {
			super();
			this.lat = lat;
			this.lng = lng;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		} 
	    
		
		

}
