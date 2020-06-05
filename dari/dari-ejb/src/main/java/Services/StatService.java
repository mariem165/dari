package Services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Interfaces.statisticsRemote;
import tn.esprit.dari.entities.Meubles;
import tn.esprit.dari.entities.Statistics;

@Stateful
@LocalBean
public class StatService implements statisticsRemote {
	
	
	@PersistenceContext (unitName="dari-ejb")
	EntityManager em;

	@Override
	public void CreateStat(Statistics S) {
		em.persist(S);
	}

	@Override
	public void DeleteStat(Statistics S) {
		em.remove(S);
		
	}

	@Override
	public List<Statistics> ShowStat() {
		List <Statistics> MM= em.createQuery("Select a from Statistics a",Statistics.class).getResultList();
		return MM;
	}

	@Override
	public void UpdateStat(int id, Statistics S) {
		
		
	}

	@Override
	public List<String> TOP5Dates() {
		List <Statistics> l= em.createQuery("Select a from Statistics a",Statistics.class).getResultList();
		List <String> dd= new ArrayList<String>();
    	List <String> DADA= new ArrayList<String>();
    	List <String> results= new ArrayList<String>();
    	if (l.size()!=0)
    		for (Statistics s : l) {
    		dd.add(s.getDate());
    	}
    	else dd.add("none");
    	
    	for (String Dates : dd) {
    		if(!DADA.contains(Dates)) {
    		DADA.add(Dates);
    		
    		}
    	
    		
    	}
    	for (int y=0; y<=4; y++) results.add(DADA.get(y));
		return results;
	}

	@Override
	public List<String> TOP5Ip() {
		List <Statistics> l= em.createQuery("Select a from Statistics a",Statistics.class).getResultList();
		List <String> dd= new ArrayList<String>();
    	List <String> DADA= new ArrayList<String>();
    	List <String> results= new ArrayList<String>();
    	if (l.size()!=0)
    		for (Statistics s : l) {
    		dd.add(s.getIp());
    	}
    	else dd.add("none");
    	
    	for (String Dates : dd) {
    		if(!DADA.contains(Dates)) {
    		DADA.add(Dates);
    		
    		}
    	
    		
    	}
    	for (int y=0; y<=4; y++) results.add(DADA.get(y));
		return results;
	}

	@Override
	public List<String> TOP5Location() {
		List <Statistics> l= em.createQuery("Select a from Statistics a",Statistics.class).getResultList();
		List <String> dd= new ArrayList<String>();
    	List <String> DADA= new ArrayList<String>();
    	List <String> results= new ArrayList<String>();
    	if (l.size()!=0)
    		for (Statistics s : l) {
    		dd.add(s.getLocation());
    	}
    	else dd.add("none");
    	
    	for (String Dates : dd) {
    		if(!DADA.contains(Dates)) {
    		DADA.add(Dates);
    		
    		}
    	
    		
    	}
    	for (int y=0; y<=4; y++) results.add(DADA.get(y));
		return results;
	}

	@Override
	public List<Meubles> TOP5Ad() {
		List <Statistics> l= em.createQuery("Select a from Statistics a",Statistics.class).getResultList();
		List <Meubles> dd4= new ArrayList<Meubles>();
    	List <Meubles> DADA4= new ArrayList<Meubles>();
    	List <Meubles> DADA5= new ArrayList<Meubles>();
    	
    	if (l.size()!=0)
    		for (Statistics s : l) {
    			
    			
    			
    		dd4.add(s.getMeubles());
    	}
    	
    	for (Meubles Dates4 : dd4) {
    		Meubles DDD =new Meubles();
    		DDD.setTitle(Dates4.getTitle());
    		DDD.setId(Dates4.getId());
    		DDD.setDescription(Dates4.getDescription());
    		DDD.setPrix(Dates4.getPrix());
    		DDD.setImage(Dates4.getImage());
    		DDD.setUser(null);
    		System.out.println(DADA4.contains(DDD));
    		System.out.println("___________________");
    		int test=0;
    		
    		for(Meubles Meuuuu: DADA4) {   			
    			if (Meuuuu.getId()==DDD.getId())test=1;
    		}
    		
    		
    		if(test==0) {
    		DADA4.add(DDD);
    		
    		}
	}
    	for (int y=0; y<=4; y++) DADA5.add(DADA4.get(y));
return DADA5;
}
}