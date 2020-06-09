package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.dari.entities.Insurence;

/**
 * Session Bean implementation class InsurenceService
 */
@Stateless
@LocalBean
public class InsurenceService implements InsurenceServiceLocal {

	@PersistenceContext
    EntityManager em; 
	
	
    /**
     * Default constructor. 
     */
    public InsurenceService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createIns(Insurence ins) {
		em.persist(ins);
		
	}

	@Override
	public List<Insurence> getall() {
		return em.createQuery("select p from Insurence p ",Insurence.class).getResultList();
    	 
	}

}
