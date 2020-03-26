package Interfaces;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;

import tn.esprit.dari.entities.Ad;

@Remote
@LocalBean
public interface IAdRemote {
	public void createAd(Ad ad);
	public void deleteAd(int id);
	public List<Ad> findAllAds();
	public void updateAd(Ad ad);
	

}
