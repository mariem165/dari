package ManagedBeans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@ManagedBean (name="meubleresh")
@SessionScoped 
@RequestScoped
public class MeubleReshBean {
	private String nafkh;

	public String getNafkh() {
		return nafkh;
	}

	public String setNafkh() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String F=params.get("reshbut");
		System.out.println(nafkh);
		
		
		this.nafkh = F;
		return "test2";
	}
	
	public String ResearchM ()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		nafkh=params.get("123");
		System.out.println(nafkh);
		
		return "test2";
		
	}

}
