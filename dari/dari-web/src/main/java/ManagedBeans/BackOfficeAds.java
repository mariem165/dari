package ManagedBeans;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Services.AdPurchaseService;
import tn.esprit.dari.entities.PurchaseAd;
import tn.esprit.dari.entities.StatusAd;

@ManagedBean(name = "backOffice")
@SessionScoped
public class BackOfficeAds implements Serializable {

	private List<PurchaseAd> listPurchase;
	private float number;
	public StatusAd statusAd;

	public StatusAd getStatusAd() {
		return statusAd;
	}

	public void setStatusAd(StatusAd statusAd) {
		this.statusAd = statusAd;
	}

	public static String toString(Enum<?> e) {
	    ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(null, "messages");
	    return bundle.getString(e.getClass().getSimpleName() + "/" + e.name());
	}
	private int idP;

	@EJB
	AdPurchaseService adPurchaseService;

	public List<PurchaseAd> getAllAds() {
		listPurchase = adPurchaseService.findAllAdsPurchase();
		return listPurchase;
	}

	public int acceptPucrchase(int id) {
		idP = adPurchaseService.Accept(id);
		return idP;
	}

	public int refusePucrchase(int id) {
		idP = adPurchaseService.Refuse(id);
		return idP;
	}

	public void supprimer(int id) {

		adPurchaseService.deleteAdPurchase(id);
		System.out.println("delete");

	}

	public float getNumber() {
		number = adPurchaseService.getNombrePost();
		return (number);
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

	public int getIdP() {
		return idP;
	}

	public void setIdP(int idP) {
		this.idP = idP;
	}

}
