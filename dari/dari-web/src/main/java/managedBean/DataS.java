package managedBean;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.dari.entities.ContractModelType;

@ManagedBean
@ApplicationScoped
public class DataS {
public ContractModelType[] getType() {
	
	return ContractModelType.values();
}
}
