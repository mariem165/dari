package Interfaces;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;

import tn.esprit.dari.entities.Contract;

@Remote
@LocalBean
public interface IContractRemote {

	public void createCn(Contract cn);
	public void deleteCn(int id);
	public List<Contract> findAllCns();
	public void updateCn(Contract cn , int id);
	public boolean screenshot();
}
