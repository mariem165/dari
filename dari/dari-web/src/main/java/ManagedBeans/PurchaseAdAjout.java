package ManagedBeans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Services.AdPurchaseService;
import tn.esprit.dari.entities.PurchaseAd;

@ManagedBean (name="purchaseBean")
@SessionScoped
public class PurchaseAdAjout implements Serializable{

	private static final long serialVersionUID = 1L;
    private String title;
	private String image;
	private String description;
	private String location;
    private Date date;
	private float price;
	
	private PurchaseAd PurchaseAd ;
	
	@EJB 
	AdPurchaseService adPurchaseService;
	
	public void createAdsPurchase() { 
		adPurchaseService.createAdPurchase(new PurchaseAd(title,image,description,location,date,price ));
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public AdPurchaseService getAdPurchaseService() {
		return adPurchaseService;
	}

	public void setAdPurchaseService(AdPurchaseService adPurchaseService) {
		this.adPurchaseService = adPurchaseService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PurchaseAdAjout() {
		super();
	}

	public PurchaseAd getPurchaseAd() {
		return PurchaseAd;
	}

	public void setPurchaseAd(PurchaseAd purchaseAd) {
		PurchaseAd = purchaseAd;
	}


	
	
	
	
}
