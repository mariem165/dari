package Services;

import javax.ejb.Stateless;
import javax.net.ssl.HostnameVerifier;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import Interfaces.IAcheterRemote;
import tn.esprit.dari.entities.Ad;
import tn.esprit.dari.entities.Adtype;
import tn.esprit.dari.entities.History;
import tn.esprit.dari.entities.User;


@Stateless
public class AcheterService implements IAcheterRemote {
	
	@PersistenceContext(unitName = "dari-ejb")
    EntityManager entityManager;
	
	
	
	@Override
	public History SaveAds(int IdAd,int idUser) {
		Ad ads = entityManager.find(Ad.class,IdAd);
		User user = entityManager.find(User.class,idUser);

		History history = new History();
		history.setUser(user);
		history.setAds(ads);
		entityManager.persist(history);
		entityManager.flush();
		return history;
		}
	
}	
