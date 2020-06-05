package Interfaces;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;


import tn.esprit.dari.entities.PurchaseAd;
import tn.esprit.dari.entities.User;


@Remote
@LocalBean
public interface IAdPurchaseRemote {

	public void createAdPurchase(PurchaseAd ad);
	public void deleteAdPurchase(int id);
	public List<PurchaseAd> findAllAdsPurchase();
	public void updateAdPurchase(PurchaseAd ad);
	public PurchaseAd findByTitle(String title);
	public int getNombrePost();
	List<PurchaseAd> findByUserAd();
	public int Accept(int id);
	public int Refuse(int id);
	public PurchaseAd findPurchaseById(int id);
	//String uploadImage(String urlFile);
}
