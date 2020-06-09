package Interfaces;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;

import tn.esprit.dari.entities.Subscription;
@Remote
@LocalBean
public interface ISubscriptionRemote {

	public void createSub(Subscription sb);
	public void deleteSub(int id);
	public List<Subscription> findAllSubs();
	public void updateSub(Subscription sb, int id);
	public List<Subscription>findSubcription(int id);
}
