package Services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.dari.entities.Location_Use_Bean;
import Interfaces.locationRemote;

@Stateful
public class IpService implements locationRemote {
	@PersistenceContext (unitName="dari-ejb")
	EntityManager em;
	

	@Override
	public List<Location_Use_Bean> CountryByRank() {
		List<Location_Use_Bean> L= em.createQuery("Select a from Location_Use_Bean a",Location_Use_Bean.class).getResultList();
		return L;
	}

}
