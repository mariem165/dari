package ManagedBeans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.dari.entities.UserType;

@ManagedBean(name = "data")
@ApplicationScoped
public class Data implements Serializable{
    private static final long serialVersionUID =1L;
 
	public UserType[] getUserTypes() {
		return UserType.values();
		
	}
}
