package Interfaces;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.dari.entities.Ad;
import tn.esprit.dari.entities.Adtype;



@Remote
@Local
public interface IAcheterRemote {
	 public Ad findByMail(String title, String location, float price ,Adtype adtype);
}
