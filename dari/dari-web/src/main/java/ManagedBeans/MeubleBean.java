package ManagedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import Services.MeublesServices;
import tn.esprit.dari.entities.Meubles;


@ManagedBean (name="MeubleBean")
@SessionScoped 
@RequestScoped

public class MeubleBean implements Serializable {
@EJB
MeublesServices ms;
public List<Meubles> list;
public String S="";
public String url;
private Meubles Meu;








public Meubles getMeu() {
	return Meu;
}

public void setMeu(Meubles meu) {
	Meu = meu;
}

///////////////////////
public String getUrl() {
	return url;
}

public String setUrl(String end) {
	/*HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String URL = request.getRequestURL().toString();*/
	//String URL="meubles2?id="+end;
    this.url=end;
    Meubles Meub= new Meubles();
    /*this.setMeu(end);*/
    Meub.setId(Integer.parseInt(end));
    Meub.setDescription(ShowDesc(end));
    Meub.setImage(ShowImg(end));
    Meub.setPrix(ShowPrix(end));
    Meub.setTitle(ShowName(end));
    this.Meu=Meub;
    System.out.println(this.Meu.getImage());
    System.out.println(this.Meu.getImage());
    return "meubles2";
}
//////////////////////

public String ShowPrix (String URL)
{
	for (Meubles Me : ms.ShowMeubles()) {
		if (Me.getId()==Integer.parseInt(URL)) return Me.getPrix();
	}
	return null;
	
}




public String ShowName (String URL)
{
	for (Meubles Me : ms.ShowMeubles()) {
		if (Me.getId()==Integer.parseInt(URL)) return Me.getTitle();
	}
	return null;
	
}
public String ShowDesc (String URL)
{
	for (Meubles Me : ms.ShowMeubles()) {
		if (Me.getId()==Integer.parseInt(URL)) return Me.getDescription();
	}
	return null;
	
	
}
public String ShowImg (String URL)
{
	for (Meubles Me : ms.ShowMeubles()) {
		if (Me.getId()==Integer.parseInt(URL)) return Me.getImage();
	}
	return null;
	
}

public List<Meubles> getList() {
	/*if (this.getS()=="") {  System.out.print(list.get(0).getTitle());
	return ms.ShowMeubles();}
	else {for (Meubles M : ms.ShowMeubles()) 
				{if ((M.getTitle()).contains(this.getS())) list.add(M);} 
		  if (list.size()!=0)  {System.out.print(list); return list;}
		  else { System.out.print(list); return ms.ShowMeubles();}}*/
	Meubles M = new Meubles();
	M.setDescription("sdsdsdss");
	M.setId(66666666);
	M.setPrix(this.getS());
	M.setTitle(this.S);
	M.setImage("123.jpg");
	if (S.equals("")==true) return ms.ShowMeubles();
	else {
		List<Meubles> K = new ArrayList<>();
		for (Meubles Me : ms.ShowMeubles()) {
			if (Me.getDescription().contains(S)) K.add(Me);
			else {if (Me.getTitle().contains(S)) K.add(Me);}
			
			}
		return K;
	}
		 
}

public void setList(List<Meubles> list) {
	this.list = list;
}



public String getS() {
	return S;
}

public String setS() {
	FacesContext fc = FacesContext.getCurrentInstance();
	Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
	String F=params.get("reshbut");
	this.S=F;
	return ("meuble");
	
}


}
