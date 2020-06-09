package Interfaces;

import java.util.Date;
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
	public List<Contract> SearchContrat(String fist_name,String start_Date,String end_Date,String last_name,String email);
	public List<Contract>RechercheContrat(String first_name, Date start_Date, Date end_Date, String last_name, String email);
}
