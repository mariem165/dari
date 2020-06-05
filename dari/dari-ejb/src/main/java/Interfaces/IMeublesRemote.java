package Interfaces;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;

import tn.esprit.dari.entities.Meubles;
@Remote
@LocalBean
public interface IMeublesRemote {
	public void CreateMeubles (Meubles M);
	public void DeleteMeubles (Meubles M);
	public List<Meubles> ShowMeubles ();
	public void UpdateMeuble (int id,Meubles m2);
}