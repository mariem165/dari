package Interfaces;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;


import tn.esprit.dari.entities.User;

@Remote
@LocalBean
public interface IUserRemote {
	
	 public void createUser(User user);
	 public void updateUser(User user);
	 public void deleteUser(int id);
	 public List<User> findAllUsers();
	 public User findUserById(int id);
	 public boolean activateAccount(String confirmationToken) ;
     public boolean changePwd(String oldPwd, String newPwd) ;
     public int AssignAdmin(int id);
     public User loginUser(String email, String pwd);
     public User findByMail(String mail);
     public boolean uploadProfileImage(String imgToUpload);
     public int getNombreUser();
}
