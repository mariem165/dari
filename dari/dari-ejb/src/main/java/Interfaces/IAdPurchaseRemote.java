package Interfaces;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;


import tn.esprit.dari.entities.PurchaseAd;


@Remote
@LocalBean
public interface IAdPurchaseRemote {

	public void createAdPurchase(PurchaseAd ad);
	public void deleteAdPurchase(int id);
	public List<PurchaseAd> findAllAdsPurchase();
	public void updateAdPurchase(PurchaseAd ad);
	public PurchaseAd findByTitle(String title);
}
