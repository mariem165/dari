package Interfaces;

import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Remote;

import tn.esprit.dari.entities.ContractModelType;

@Remote
@LocalBean
public interface IStatRemote {

	public Map<ContractModelType,String> contractTypeStat();
}
