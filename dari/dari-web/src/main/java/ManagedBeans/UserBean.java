package ManagedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Services.UserService;
import tn.esprit.dari.entities.PurchaseAd;
import tn.esprit.dari.entities.User;
import tn.esprit.dari.entities.UserType;

@ManagedBean (name="userBean")
@SessionScoped

public class UserBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String first_name;

	private String last_name;
	private String email;
	private String password;
	private Date createdAt;
	private String address;
	private String Ntelephone ;
	private UserType usertype ;
	private User user;

	private Integer UserIdToBeUpdated; 
	private float number;
	private List<User> listUser;
	
	public static User Iuser = new User();
	@EJB 
	UserService userService; 


	
public float getNumber() {
	number=userService.getNombreUser();
	return(number);
}

	public void supprimer(int id) {

		userService.deleteUser(id);
		System.out.println("delete");

	}

	public List<User> getUsers() {
		listUser=userService.findAllUsers();
		System.out.println(listUser);
		return listUser;
	}

	public User getUserByID(int id) {
		User user= userService.findUserById(id);
		return user;
	}

	public String displayUser(User user) {
		UserBean.Iuser.setFirst_name(user.getFirst_name());
		UserBean.Iuser.setLast_name(user.getLast_name());
		UserBean.Iuser.setEmail(user.getEmail());
		UserBean.Iuser.setAddress(user.getAddress());
		UserBean.Iuser.setNtelephone(user.getNtelephone());
		UserBean.Iuser.setPassword(user.getPassword());
		System.out.println();
		String navigateTo = "UpdateUser?faces-redirect=true";
		return navigateTo;
	}

	public void updateUser() { 
		userService.updateUser(Iuser); } 


	public User getUserByMail(String mail) {
		User user= userService.findByMail(mail);
		return user;
	} 

	public String getFirst_name() {
		return first_name;
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

	
	public void setNumber(float number) {
		this.number = number;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public UserBean() {
		super();
	}



}
