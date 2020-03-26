package Services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.dari.entities.Ad;
import Interfaces.IAdRemote;

@Stateful
public class AdService implements IAdRemote {
	
	@PersistenceContext(unitName= "dari-ejb")
	EntityManager entityManager;
	
	@Override
	public void createAd(Ad ad) {
		entityManager.persist(ad);
	}
	 
	@Override
	public void deleteAd(int id) {
		entityManager.remove(entityManager.find(Ad.class, id));
		
	}
	@Override
	public List<Ad> findAllAds(){
		List<Ad> ad = entityManager.createQuery("from Ad" , Ad.class).getResultList();
		return ad ;
		
	}
	@Override
	public void updateAd(Ad ad) {
	
	        Ad a = entityManager.find(Ad.class, ad.getId());
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
	        if (ad.getSeller() != null) {
	            a.setSeller(ad.getSeller());
	        }
	        if (ad.getRating() != null) {
	            a.setRating(ad.getRating());
	        }
	 
	    }
	}
