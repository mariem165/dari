package ManagedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Services.AdPurchaseService;
import tn.esprit.dari.entities.PurchaseAd;


@ManagedBean (name="backOffice")
@SessionScoped
public class BackOfficeAds implements Serializable {

private List<PurchaseAd> listPurchase;
private float number;
	
    @EJB 
	AdPurchaseService adPurchaseService;
	
	public List<PurchaseAd> getAllAds() {
		listPurchase=adPurchaseService.findAllAdsPurchase();
		return listPurchase;
	}
	
	
	public float getNumber() {
		number=adPurchaseService.getNombrePost();
		return(number);
	}

	public List<PurchaseAd> getListPurchase() {
		return listPurchase;
	}

	public void setListPurchase(List<PurchaseAd> listPurchase) {
		this.listPurchase = listPurchase;
	}

	public AdPurchaseService getAdPurchaseService() {
		return adPurchaseService;
	}

	public void setAdPurchaseService(AdPurchaseService adPurchaseService) {
		this.adPurchaseService = adPurchaseService;
	}

	
	public void setNumber(float number) {
		this.number = number;
	}


	public BackOfficeAds() {
		super();
	}
	
	
	
}
