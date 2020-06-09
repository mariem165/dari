package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Interfaces.IContractRemote;
import Interfaces.IStatRemote;
import Interfaces.IUserRemote;
import tn.esprit.dari.entities.Contract;
import tn.esprit.dari.entities.ContractModelType;
import tn.esprit.dari.entities.Subscription;
import tn.esprit.dari.entities.User;

@ManagedBean(name = "contractBean")
@SessionScoped
public class ContractBean implements Serializable {

	private String first_name;
	private String last_name;
	private String email;
	private String start_Date;
	private String end_Date;
	private Date end_Datee;
	private Date start_Datee;
	@EJB
	IStatRemote statService;
	@EJB
	IContractRemote contractService;
	@EJB
	IUserRemote userService;
	User user;
	Contract contract;
	List<Contract> lstcontract;
	List<User> lstUser;
	List<Contract>listedesContrat;
	Map<ContractModelType, String> statMap = new HashMap<>();

	public List<User> getLstUser() {
		return lstUser;
	}

	public void setLstUser(List<User> lstUser) {
		this.lstUser = lstUser;
	}

	@PostConstruct
	public void init() {
		contract = new Contract();
		this.getAllContract();
		this.lstUser = userService.findAllUsers();
	}

	
	
	public Date getEnd_Datee() {
		return end_Datee;
	}

	public void setEnd_Datee(Date end_Datee) {
		this.end_Datee = end_Datee;
	}

	public Date getStart_Datee() {
		return start_Datee;
	}

	public void setStart_Datee(Date start_Datee) {
		this.start_Datee = start_Datee;
	}

	public void searchContract() {

		this.lstcontract = contractService.RechercheContrat(first_name, start_Datee, end_Datee, last_name, email);
	}

	public void getAllContract() {

		this.lstcontract = contractService.findAllCns();
		if (lstcontract == null)
			lstcontract = new ArrayList<Contract>();
	}

	public List<String> getAllStat() {
		statMap = statService.contractTypeStat();
		List<String> stm = new ArrayList<String>();
		for (Map.Entry<ContractModelType, String> entry : statMap.entrySet()) {
			System.out.println(entry.getKey().toString());
			stm.add(entry.getKey().toString());

		}
		for (Map.Entry<ContractModelType, String> entry : statMap.entrySet()) {
			stm.add(entry.getValue());
		}
		System.out.println(stm);
		return stm;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public List<Contract> getLstcontract() {
		return lstcontract;
	}

	public void setLstcontract(List<Contract> lstcontract) {
		this.lstcontract = lstcontract;
	}

	public void addContract() {
		System.out.println(contract);
		this.contract.setUser(this.user);
		contractService.createCn(this.contract);
		this.contract = new Contract();
		this.user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String addUser(User u) {
		this.user = u;
		return "addContract?faces-redirect=true";
	}

	public String addcont(Contract c) {
		this.contract = c;
		return "updateContract?faces-redirect=true";
	}

	public String updateConract() {
		this.contractService.updateCn(this.contract, this.contract.getId());
		return "AddAbonnement?faces-redirect=true";
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(String start_Date) {
		this.start_Date = start_Date;
	}

	public String getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(String end_Date) {
		this.end_Date = end_Date;
	}

public List<Contract>listdescont(){
	
	return this.lstcontract = contractService.findAllCns() ;
}
}
