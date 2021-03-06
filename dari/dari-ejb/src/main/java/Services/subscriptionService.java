package Services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.dari.entities.Subscription;
import Interfaces.ISubscriptionRemote;

@Stateful
public class subscriptionService implements ISubscriptionRemote {

	@PersistenceContext(unitName = "dari-ejb")
	EntityManager entityManager;

	@Override
	public void createSub(Subscription sub) {
		entityManager.persist(sub);
	}
	/*@Override
    public void createUser(User user) {
        user.setPassword(MD5Hash.getMD5Hash(user.getPassword()));
        String activationHashedCode = MD5Hash.getMD5Hash(user.getNtelephone()+ user.getEmail());
        user.setConfirmationToken(activationHashedCode);
        user.setCreatedAt(new Date());
        entityManager.persist(user);
    }*/

	@Override
	public void deleteSub(int id) {
		entityManager.remove(entityManager.find(Subscription.class, id));

	}

	@Override
	public List<Subscription> findAllSubs() {
		List<Subscription> sub = entityManager.createQuery("from Subscription", Subscription.class).getResultList();
		return sub;

	}

	@Override
	public void updateSub(Subscription sub, int id) {

		Subscription s = entityManager.find(Subscription.class, id);
		if (sub.getUser() != null) {
			s.setUser(sub.getUser());
		}

		if (sub.getInsurence() != null) {
			s.setInsurence(sub.getInsurence());
		}

	}

	@Override
	public List<Subscription> findSubcription(int id) {
		TypedQuery<Subscription>query;
		
		query=entityManager.createQuery("SELECT e FROM Subscription e WHERE e.user.id=:user",Subscription.class);
		
		query.setParameter("user", id);
		List<Subscription> sub=null;
		try {
		sub=query.getResultList();
		}catch(NoResultException e) {
			System.out.println("erreur ");
		}
		return sub;
	}
}
