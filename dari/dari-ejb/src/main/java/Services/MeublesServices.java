package Services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Interfaces.IMeublesRemote;
import tn.esprit.dari.entities.Meubles;


@Stateful
public class MeublesServices implements IMeublesRemote {
	
	@PersistenceContext (unitName="dari-ejb")
	EntityManager em;
	

	@Override
	public void CreateMeubles(Meubles M) {
		
		em.persist(M);
	}

	@Override
	public void DeleteMeubles(Meubles M) {
		
		em.remove(M);
		
	}

	@Override
	public List<Meubles> ShowMeubles() {
		List <Meubles> MM= em.createQuery("Select a from Meubles a",Meubles.class).getResultList();
		return MM;
	}

	@Override
	public void UpdateMeuble( int id,Meubles m2) {
		 Meubles m = em.find(Meubles.class, id);
		 if (m2.getDescription()!=null)m.setDescription(m2.getDescription());
		 if (m2.getImage()!=null) m.setImage(m2.getImage());
		 if (m2.getPrix()!=null)m.setPrix(m2.getPrix());
		
	}

}