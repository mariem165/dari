package Services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.dari.entities.Contract;
import tn.esprit.dari.entities.ContractModelType;
import Interfaces.IContractRemote;


@Stateful
public class ContractService implements IContractRemote {

	@PersistenceContext(unitName = "dari-ejb")
	EntityManager entityManager;

	@Override
	public void createCn (Contract con) {
		ContractModelType ct = con.getContract_model_type();
		System.out.println(ct);
		entityManager.persist(con);
	}

	@Override
	public void deleteCn(int id) {
		entityManager.remove(entityManager.find(Contract.class, id));
		
	}

	@Override
	public List<Contract> findAllCns() {
		List<Contract> con = entityManager.createQuery("from Contract", Contract.class).getResultList();
		return con;

	}

	@Override
	public void updateCn(Contract cn, int id) {
		Contract c = entityManager.find(Contract.class, id);
		if (cn.getUser() != null) {
			c.setUser(cn.getUser());
		}

		if (cn.getContract_model_type() != null) {
			c.setContract_model_type(cn.getContract_model_type());
		}
	}

	/*@Override
    public void createUser(User user) {
        user.setPassword(MD5Hash.getMD5Hash(user.getPassword()));
        String activationHashedCode = MD5Hash.getMD5Hash(user.getNtelephone()+ user.getEmail());
        user.setConfirmationToken(activationHashedCode);
        user.setCreatedAt(new Date());
        entityManager.persist(user);
    }*/

	
}
