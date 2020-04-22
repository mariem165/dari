package Interfaces;

import javax.ejb.LocalBean;
import javax.ejb.Remote;

import tn.esprit.dari.entities.Ad;
import tn.esprit.dari.entities.Adtype;
import tn.esprit.dari.entities.History;



@Remote
@LocalBean
public interface IAcheterRemote {
	public History SaveAds(int IdAd,int idUser);
}
