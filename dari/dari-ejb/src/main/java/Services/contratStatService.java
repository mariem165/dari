package Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Interfaces.IStatRemote;
import tn.esprit.dari.entities.Comment;
import tn.esprit.dari.entities.Contract;
import tn.esprit.dari.entities.ContractModelType;

@Stateful
public class contratStatService implements IStatRemote {
	@PersistenceContext(unitName = "dari-ejb")
	EntityManager entityManager;

	@Override
	public Map<ContractModelType, String> contractTypeStat() {
		List<Contract> cts = entityManager.createQuery("from Contract", Contract.class).getResultList();
		float lo = 0;
		float vt = 0;
		float gr = 0;

		for (Contract contract : cts) {
			if (contract.getContract_model_type()!=null) {
				if (contract.getContract_model_type().toString().equals("location")) {
					lo++;
				} else if (contract.getContract_model_type().toString().equals("vente")) {
					vt++;
				} else if (contract.getContract_model_type().toString().equals("garants")) {
					gr++;
				}
			}
		}
		Map<ContractModelType, String> statmap = new HashMap<>();
		lo = lo / cts.size();
		statmap.put(ContractModelType.location, lo * 100 + "%");
		vt = vt / cts.size();
		statmap.put(ContractModelType.vente, vt * 100 + "%");
		gr = gr / cts.size();
		statmap.put(ContractModelType.garants, gr * 100 + "%");
		return statmap;
	}
}
