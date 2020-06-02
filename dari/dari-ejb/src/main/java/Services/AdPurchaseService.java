package Services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import Interfaces.IAdPurchaseRemote;

import tn.esprit.dari.entities.PurchaseAd;



@Stateful
public class AdPurchaseService implements IAdPurchaseRemote {

	@PersistenceContext(unitName= "dari-ejb")
	EntityManager entityManager;
	
	@Override
	public void createAdPurchase(PurchaseAd ad) {
		entityManager.persist(ad);
	}
	 
	@Override
	public void deleteAdPurchase(int id) {
		entityManager.remove(entityManager.find(PurchaseAd.class, id));
		
	}
	@Override
	public List<PurchaseAd> findAllAdsPurchase(){
		List<PurchaseAd> ad = entityManager.createQuery("from PurchaseAd" , PurchaseAd.class).getResultList();
		return ad ;
		
	}
	@Override
	public void updateAdPurchase(PurchaseAd ad) {
	
		PurchaseAd a = entityManager.find(PurchaseAd.class, ad.getId());
	        if (ad.getTitle() != null) {
	           a.setTitle(ad.getTitle());
	        }
	        if (ad.getImage() != null) {
	            a.setImage(ad.getImage());
	        }
	        if (ad.getDescription() != null) {
	            a.setDescription(ad.getDescription());
	            }
	        
	        if (ad.getDate() != null) {
	            a.setDate(ad.getDate());
	        }
	        if (ad.getLocation() != null) {
	            a.setLocation(ad.getLocation());
	        }
	        
}
	
	 @Override
	   	public PurchaseAd findByTitle(String title) {
	   		Query query = entityManager.createQuery(
	   				"SELECT new PurchaseAd(u.id,u.title,u.image,u.description,u.date,u.price,"
	   				+ "u.statusad,u.location) " 
	   		+ "FROM PurchaseAd u WHERE u.title=:param");
	   		PurchaseAd u = null;
	   		try {
	   			u = (PurchaseAd) query.setParameter("param", title).getSingleResult();
	   			return u;
	   		} catch (Exception e) {
	   			System.out.println("\n\n\n\n\n\n Title Not Found | Ad Not Set \n\n\n\n\n\n ");
	   			return null;
	   		}
	   	}
	
	
	}




