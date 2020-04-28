package ManagedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Services.UserService;
import tn.esprit.dari.entities.User;
import tn.esprit.dari.entities.UserType;

@ManagedBean 
@SessionScoped
public class UserBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String first_name;
	
	private String last_name;
	private String email;
	private String password;
	
    private String address;
	private String Ntelephone ;
	private UserType usertype ;
	private User user;
	
	private Integer UserIdToBeUpdated; 
	private List<User> listUser;
	@EJB 
	UserService userService; 
	
	
	public void addUser() { userService.createUser(new User(first_name,last_name,email,password,address,Ntelephone,usertype)); }
	
	public String getFirst_name() {
		return first_name;
	}
	
public void supprimer(int id) {
		
		userService.deleteUser(id);
		System.out.println("delete");
		
	}

public List<User> getUsers() {
	listUser=userService.findAllUsers();
	return listUser;
}

public User getUserByID(int id) {
	User user= userService.findUserById(id);
	return user;
}

public void displayUser(User user) {
	   this.setFirst_name(user.getFirst_name());
	   this.setLast_name(user.getLast_name());
	   this.setEmail(user.getEmail());
	   this.setAddress(user.getAddress());
	   this.setNtelephone(user.getNtelephone());
	   this.setPassword(user.getPassword());
	   this.setUserIdToBeUpdated(user.getId());
}

public void updateUser() { 
	userService.updateUser(new User(UserIdToBeUpdated, first_name, last_name, email, password, Ntelephone, address)); } 


public User getUserByMail(String mail) {
	User user= userService.findByMail(mail);
	return user;
} 


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNtelephone() {
		return Ntelephone;
	}
	public void setNtelephone(String ntelephone) {
		Ntelephone = ntelephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public UserType getUsertype() {
		return usertype;
	}
	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public Integer getUserIdToBeUpdated() {
		return UserIdToBeUpdated;
	}

	public void setUserIdToBeUpdated(Integer userIdToBeUpdated) {
		UserIdToBeUpdated = userIdToBeUpdated;
	}
	
	

}
