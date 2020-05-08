package ManagedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import Services.MeublesServices;
import tn.esprit.dari.entities.Meubles;

@Named("MeubleBean")
@ManagedBean 
@SessionScoped 
public class MeubleBean implements Serializable {
@EJB
MeublesServices ms;
String E;
public List<Meubles> list;
public static String S="";

public String getE() {
	return E;
}

public void setE(String e) {
	E = e;
}

public String ShowPrix (int a)
{
	List<Meubles> list=ms.ShowMeubles();
	FacesContext.getCurrentInstance().addMessage("form:mbl", new FacesMessage("10"/*list.get(0).getPrix()*/));
	return (list.get(a).getPrix()+" DT");
	
}

public String ShowName (int a)
{
	List<Meubles> list=ms.ShowMeubles();
	FacesContext.getCurrentInstance().addMessage("form:mbl", new FacesMessage("10"/*list.get(0).getPrix()*/));
	return list.get(a).getTitle();
	
}
public String ShowDesc (int a)
{
	List<Meubles> list=ms.ShowMeubles();
	FacesContext.getCurrentInstance().addMessage("form:mbl", new FacesMessage("10"/*list.get(0).getPrix()*/));
	return list.get(a).getDescription();
	
}
public String ShowImg (int a)
{
	List<Meubles> list=ms.ShowMeubles();
	FacesContext.getCurrentInstance().addMessage("form:mbl", new FacesMessage("10"/*list.get(0).getPrix()*/));
	return list.get(a).getImage();
	
}

public List<Meubles> getList() {
	/*if (this.getS()=="") {  System.out.print(list.get(0).getTitle());
	return ms.ShowMeubles();}
	else {for (Meubles M : ms.ShowMeubles()) 
				{if ((M.getTitle()).contains(this.getS())) list.add(M);} 
		  if (list.size()!=0)  {System.out.print(list); return list;}
		  else { System.out.print(list); return ms.ShowMeubles();}}*/
	return ms.ShowMeubles();
		 
}

public void setList(List<Meubles> list) {
	this.list = list;
}

public Meubles searchM ()
{
	return null;
	
}

public String getS() {
	return S;
}

public void setS(String s) {
	S = s;
}

	
}
