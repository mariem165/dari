package Services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import Interfaces.IAcheterRemote;
import tn.esprit.dari.entities.Ad;
import tn.esprit.dari.entities.Adtype;
import tn.esprit.dari.entities.User;

@Stateless
public class AcheterService implements IAcheterRemote {
	
	@PersistenceContext(unitName = "dari-ejb")
    EntityManager entityManager;
	
	
	public Ad findByMail(String title, String location, float price ,Adtype adtype) {
		Query query = entityManager.createQuery(
				"SELECT new Ad(u.id,u.user,u.title,u.image,u.description,u.location,"
				+ "u.price,u.rating,u.adtype) " 
		+ "FROM Ad u WHERE u.title=:param");
		/*User u = null;
		try {
			u = (User) query.setParameter("param", mail).getSingleResult();
			return u;
		} catch (Exception e) {
			System.out.println("\n\n\n\n\n\n mail Not Found | User Not Set \n\n\n\n\n\n ");*/
			return null;
		}
	//}
}
