package Services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.dari.entities.Insurence;

@Local
public interface InsurenceServiceLocal {

	 void createIns(Insurence ins);
		List<Insurence> getall();
}
