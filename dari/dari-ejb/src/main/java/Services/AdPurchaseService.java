package Services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.imageio.ImageIO;
import javax.mail.Part;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import Interfaces.IAdPurchaseRemote;

import tn.esprit.dari.entities.PurchaseAd;
import tn.esprit.dari.entities.StatusAd;
import tn.esprit.dari.entities.User;
import tn.esprit.dari.entities.UserType;

@Stateful
@LocalBean
public class AdPurchaseService implements IAdPurchaseRemote {

	@PersistenceContext(unitName = "dari-ejb")
	EntityManager entityManager;

	@Override
	public void createAdPurchase(PurchaseAd ad) {
		ad.setUser(UserService.UserLogged);
		ad.setDate(new Date());
		ad.setStatusad(StatusAd.pending);
		entityManager.persist(ad);
	}

	@Override
	public void deleteAdPurchase(int id) {
		entityManager.remove(entityManager.find(PurchaseAd.class, id));

	}

	@Override
	public List<PurchaseAd> findAllAdsPurchase() {
		List<PurchaseAd> ad = entityManager.createQuery("from PurchaseAd ", PurchaseAd.class).getResultList();
		return ad;

	}

	@Override
	public void updateAdPurchase(PurchaseAd ad) {

		PurchaseAd a = entityManager.find(PurchaseAd.class, ad.getId());

		
			a.setId(ad.getId());
	
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
		Query query = entityManager
				.createQuery("SELECT new PurchaseAd(u.id,u.title,u.image,u.description,u.date,u.price,"
						+ "u.statusad,u.location) " + "FROM PurchaseAd u WHERE u.title=:param");
		PurchaseAd u = null;
		try {
			u = (PurchaseAd) query.setParameter("param", title).getSingleResult();
			return u;
		} catch (Exception e) {
			System.out.println("\n\n\n\n\n\n Title Not Found | Ad Not Set \n\n\n\n\n\n ");
			return null;
		}
	}
	/*
	 * public static Collection<Part> getAllParts(Part part) throws
	 * ServletException, IOException { HttpServletRequest request =
	 * (HttpServletRequest)
	 * FacesContext.getCurrentInstance().getExternalContext().getRequest(); return
	 * request.getParts().stream().filter(p ->
	 * part.getName().equals(p.getName())).collect(Collectors.toList()); }
	 */
	
	@Override
	public List<PurchaseAd> findByUserAd() {
		Query query = entityManager
				.createQuery("SELECT new PurchaseAd(u.id,u.title,u.image,u.description,u.date,u.price,"
						+ "u.statusad,u.location) " + "FROM PurchaseAd u WHERE u.id=:param");
		List<PurchaseAd> u = null;
		try {
			u =  query.setParameter("param", UserService.UserLogged.getId()).getResultList();
			return u;
		} catch (Exception e) {
			System.out.println("\n\n\n\n\n\n Title Not Found | Ad Not Set \n\n\n\n\n\n ");
			return null;
		}
	}
	
	@Override
	public int getNombrePost() {
		Query query = entityManager.createQuery("Select Count(e) from PurchaseAd e");
		return ((Number) query.getSingleResult()).intValue();
	}

	
	@Override
	public int Accept(int id) {
		PurchaseAd purchaseAd = findPurchaseById(id);
		purchaseAd.setStatusad(StatusAd.accepted);
		entityManager.merge(purchaseAd);

		return purchaseAd.getId();
	}
	
	@Override
	public PurchaseAd findPurchaseById(int id) {

		PurchaseAd purchaseAd = entityManager.find(PurchaseAd.class, id);
		return purchaseAd;
	}
	
	@Override
	public int Refuse(int id) {
		PurchaseAd purchaseAd = findPurchaseById(id);
		purchaseAd.setStatusad(StatusAd.refused);
		entityManager.merge(purchaseAd);

		return purchaseAd.getId();
	}
}
