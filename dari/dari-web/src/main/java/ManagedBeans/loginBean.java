package ManagedBeans;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Services.UserService;
import tn.esprit.dari.entities.User;
import tn.esprit.dari.entities.UserType;

@ManagedBean 
@SessionScoped 
public class loginBean implements Serializable { 
	private String email; 
	private String pwd; 
	private User user; 
	private Boolean loggedIn;
	////zedha coca
	static User current;
	@EJB 
	UserService userService; 

	public String doLogin() { 
		String navigateTo = "null"; 
		user = userService.loginUser(email, pwd); 
		if (user != null && user.getUsertype() == UserType.admin) { 
			navigateTo = "/template/dashboard/main?faces-redirect=true"; loggedIn = true;
		////zedha coca
			current=user;
			} 
		else if ((user != null && user.getUsertype() == UserType.bayer ) ||(user != null && user.getUsertype() == UserType.owner )
				||(user != null && user.getUsertype() == UserType.renter )){
			navigateTo = "index?faces-redirect=true"; loggedIn = true;
			////zedha coca
			current=user;
		}
		else {
				FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));
				} 
		return navigateTo;
		}
	
	
	public String doLogout() {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); return "/login?faces-redirect=true"; 
			}
		
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public Boolean getLoggedIn() {
			return loggedIn;
		}
		public void setLoggedIn(Boolean loggedIn) {
			this.loggedIn = loggedIn;
		}
		public UserService getUserService() {
			return userService;
		}
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		
		public loginBean() {}
		
		
}