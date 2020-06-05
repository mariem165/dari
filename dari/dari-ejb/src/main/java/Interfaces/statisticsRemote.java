package Interfaces;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;

import tn.esprit.dari.entities.Meubles;
import tn.esprit.dari.entities.Statistics;
@Remote
@LocalBean
public interface statisticsRemote {
	
	public void CreateStat (Statistics S);
	public void DeleteStat (Statistics S);
	public List<Statistics> ShowStat ();
	public void UpdateStat (int id,Statistics S);
	public List<Meubles> TOP5Ad ();
	public List<String> TOP5Ip ();
	public List<String> TOP5Location ();
	public List<String> TOP5Dates ();

}
