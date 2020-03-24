package Services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Interfaces.IUserRemote;
import Utils.MD5Hash;
import tn.esprit.dari.entities.AccountState;
import tn.esprit.dari.entities.User;
import tn.esprit.dari.entities.UserType;


@Stateful
public class UserService implements IUserRemote{

	
	
	@PersistenceContext(unitName = "dari-ejb")
    EntityManager entityManager;

    public static User UserLogged;
   
    public UserService() {

    }

    @Override
    public void createUser(User user) {
        user.setPassword(MD5Hash.getMD5Hash(user.getPassword()));
        String activationHashedCode = MD5Hash.getMD5Hash(user.getNtelephone()+ user.getEmail());
        user.setConfirmationToken(activationHashedCode);
        user.setCreatedAt(new Date());
        entityManager.persist(user);
    }
    
    @Override
    public boolean activateAccount(String confirmationToken) {
        Query query = entityManager.createQuery(
                "SELECT new User(u.id,u.confirmationToken) " + "FROM User u WHERE u.confirmationToken=:param");
        User u = null;
        try {
            u = (User) query.setParameter("param", confirmationToken).getSingleResult();

            if (u != null && u.getConfirmationToken().equals(confirmationToken)) {
                User userTochange = findUserById(u.getId());
                userTochange.setAccountState(AccountState.ACTIVATED);
                updateUser(userTochange);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n confirmationToken Not Found | User Not Set \n\n\n\n\n\n ");
            return false;
        }

    }

    @Override
    public void updateUser(User user) {
        User p = entityManager.find(User.class, user.getId());
        if (user.getEmail() != null) {
            p.setEmail(user.getEmail());
        }
        if (user.getFirst_name() != null) {
            p.setFirst_name(user.getFirst_name());
        }
        if (user.getLast_name() != null) {
            p.setLast_name(user.getLast_name());
        }
        if (user.getNtelephone() != null) {
            p.setNtelephone(user.getNtelephone());
        }
 
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
     }
    
    @Override
    public List<User> findAllUsers() {
       List<User> user = entityManager.createQuery("from User",User.class).getResultList();
       return user;
    }
    
    @Override
    public User findUserById(int id) {
    	
    		User user=entityManager.find(User.class,id);
    		return user;
    	}
    @Override
    public boolean changePwd(User user, String oldPwd, String newPwd) {
        System.out.println("\n\n\n\n\n\n\n\n u : "+user.getId()+"\n\n\n\n\n\n\n\n\n");
        Query query = entityManager.createQuery(
                "SELECT new User(u.id,u.first_name,u.last_name,u.Ntelephone,u.email,u.password) "
                        + "FROM User u WHERE  (u.id=:uid)");
        User userToChnage = (User) query.setParameter("uid", user.getId()).getSingleResult();
        if (user.getPassword().equals(MD5Hash.getMD5Hash(oldPwd))) {
            user.setPassword(MD5Hash.getMD5Hash(newPwd));
            entityManager.merge(user);
            return true;
        }
        return false;
    }
    
    

    

}
