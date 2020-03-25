package Interfaces;

import java.util.List;

import tn.esprit.dari.entities.Meubles;

public interface IMeublesRemote {
	public void CreateMeubles (Meubles M);
	public void DeleteMeubles (Meubles M);
	public List<Meubles> ShowMeubles ();
	public void UpdateMeuble (int id,Meubles m2);
}