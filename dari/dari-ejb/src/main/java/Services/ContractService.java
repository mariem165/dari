package Services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.Stateful;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.bridj.util.StringUtils;

import tn.esprit.dari.entities.Contract;
import tn.esprit.dari.entities.ContractModelType;
import tn.esprit.dari.entities.User;
import Interfaces.IContractRemote;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

@Stateful
public class ContractService implements IContractRemote {

	@PersistenceContext(unitName = "dari-ejb")
	EntityManager entityManager;

	@Override
	public List<Contract> SearchContrat(String first_name, String start_Date, String end_Date, String last_name, String email) {

		
		java.util.Date d1 = null;
		java.util.Date d2 = null;
		java.sql.Date sd = new java.sql.Date(System.currentTimeMillis());
		java.sql.Date ed = new java.sql.Date(System.currentTimeMillis());
		try {
			d1 = new SimpleDateFormat("yyyy-MM-dd").parse(start_Date);
			d2 = new SimpleDateFormat("yyyy-MM-dd").parse(end_Date);
			sd = new java.sql.Date(d1.getTime());
			ed = new java.sql.Date(d2.getTime());
		} catch (ParseException e) {
			System.out.println(e);
		}
		if ((!start_Date.equals("")) && (end_Date.equals(""))) {
			System.out.println("c1");
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd").parse(start_Date);
				sd = new java.sql.Date(d1.getTime());
			} catch (ParseException e) {
				System.out.println(e);
			}
			TypedQuery<Contract> query = entityManager.createQuery(
					"select c from Contract c where start_Date = :start_Date AND c.user.first_name Like :first_name AND c.user.last_name Like :last_name AND c.user.email Like :email",
					Contract.class);
			query.setParameter("first_name", "%" + first_name + "%");
			query.setParameter("last_name", "%" + last_name + "%");
			query.setParameter("email", "%" + email + "%");
			query.setParameter("start_Date", sd);
			return query.getResultList();
		} else if (!end_Date.equals("") && start_Date.equals("")) {
			try {
				d2 = new SimpleDateFormat("yyyy-MM-dd").parse(end_Date);
				ed = new java.sql.Date(d2.getTime());
			} catch (ParseException e) {
				System.out.println(e);
			}
			System.out.println("c2");
			System.out.println(d2);
			System.out.println(ed);
			TypedQuery<Contract> query = entityManager.createQuery(
					"select c from Contract c where start_Date = :start_Date AND c.user.first_name Like :first_name AND c.user.last_name Like :last_name AND c.user.email Like :email",
					Contract.class);
			query.setParameter("first_name", "%" + first_name + "%");
			query.setParameter("last_name", "%" + last_name + "%");
			query.setParameter("email", "%" + email + "%");
			query.setParameter("end_Date", ed);
			return query.getResultList();
		} else if (!end_Date.equals("") && !start_Date.equals("")) {
			System.out.println("c3");
			TypedQuery<Contract> query = entityManager.createQuery(
					"select c from Contract c where start_Date = :start_Date AND end_Date = :end_Date AND c.user.first_name Like :first_name AND c.user.last_name Like :last_name AND c.user.email Like :email",
					Contract.class);
			query.setParameter("first_name", "%" + first_name + "%");
			query.setParameter("last_name", "%" + last_name + "%");
			query.setParameter("email", "%" + email + "%");
			query.setParameter("end_Date", ed);
			query.setParameter("start_Date", sd);
			return query.getResultList();
		} else {
			System.out.println("c4");
			TypedQuery<Contract> query = entityManager
					.createQuery("select c from Contract c where c.user.first_name Like :first_name AND c.user.last_name Like :last_name AND c.user.email Like :email", Contract.class);
			
			System.out.println(first_name);
			System.out.println(last_name);
			System.out.println(email);
			query.setParameter("first_name", "%" + first_name + "%");
			query.setParameter("last_name", "%" + last_name + "%");
			query.setParameter("email", "%" + email + "%");
			return query.getResultList(); 
		}
	}

	@Override
	public void createCn(Contract con) {
		ContractModelType ct = con.getContract_model_type();
		System.out.println(ct);
		entityManager.persist(con);
	}

	@Override
	public void deleteCn(int id) {
		entityManager.remove(entityManager.find(Contract.class, id));

	}

	@Override
	public List<Contract> findAllCns() {
		List<Contract> con = entityManager.createQuery("from Contract", Contract.class).getResultList();
		return con;

	}

	@Override
	public void updateCn(Contract cn, int id) {
		Contract c = entityManager.find(Contract.class, id);
		if (cn.getUser() != null) {
			c.setUser(cn.getUser());
		}

		if (cn.getContract_model_type() != null) {
			c.setContract_model_type(cn.getContract_model_type());
		}
	}

	/*
	 * public boolean screenshot2() {
	 * 
	 * Webcam webcam = Webcam.getDefault(); webcam.getLock().disable();
	 * 
	 * System.out.println(webcam.isOpen());
	 * 
	 * webcam.open(); System.out.println(webcam.isOpen());
	 * 
	 * // get image BufferedImage image = webcam.getImage(); String timeStamp = new
	 * SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()); String filename =
	 * "PNG_" + timeStamp + "_.png"; // save image to PNG file try { File file = new
	 * File("C:\\Users\\ASUS\\Pictures\\Saved Pictures\\"+filename);
	 * ImageIO.write(image, "PNG", file); WebcamUtils.capture(webcam, file);
	 * //webcam.getImage().getProperty("test1.png"); } catch (IOException e) {
	 * //webcam.close();
	 * 
	 * // TODO: handle exception } return true;
	 * 
	 * 
	 * }
	 * 
	 * @Override public boolean screenshot() { TimerTask task = new TimerTask() {
	 * 
	 * @Override public void run() { screenshot2(); } };
	 * 
	 * Timer timer = new Timer(); timer.schedule(task, new Date(), 3000); return
	 * true; }
	 */

	@Override
	public boolean screenshot() {

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				Webcam webcam = Webcam.getDefault();
				webcam.getLock().disable();

				System.out.println(webcam.isOpen());

				webcam.open();
				System.out.println(webcam.isOpen());

				// get image
				BufferedImage image = webcam.getImage();
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
				String filename = "PNG_" + timeStamp;
				filename = filename.replace(":", "-");
				System.out.println(filename);
				// save image to PNG file
				try {
					File file = new File("C:\\Users\\ASUS\\Pictures\\Saved Pictures\\" + filename + ".png");
					ImageIO.write(image, "PNG", file);
					WebcamUtils.capture(webcam, file);

					// webcam.getImage().getProperty("test1.png");

				} catch (IOException e) {
					// webcam.close();

					// TODO: handle exception
				}
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, new Date(), 3000);
		return true;

	}

	/*
	 * @Override public void createUser(User user) {
	 * user.setPassword(MD5Hash.getMD5Hash(user.getPassword())); String
	 * activationHashedCode = MD5Hash.getMD5Hash(user.getNtelephone()+
	 * user.getEmail()); user.setConfirmationToken(activationHashedCode);
	 * user.setCreatedAt(new Date()); entityManager.persist(user); }
	 */
@Override
	public List<Contract>RechercheContrat(String first_name, Date start_Date, Date end_Date, String last_name, String email){
	if (!end_Date.equals("") && !start_Date.equals("")) {
		TypedQuery<Contract> query = entityManager.createQuery(
				"select c from Contract c where start_Date = :start_Date AND end_Date = :end_Date AND c.user.first_name Like :first_name AND c.user.last_name Like :last_name AND c.user.email Like :email",
				Contract.class);
		query.setParameter("first_name", "%" + first_name + "%");
		query.setParameter("last_name", "%" + last_name + "%");
		query.setParameter("email", "%" + email + "%");
		query.setParameter("start_Date", start_Date);
		query.setParameter("end_Date", end_Date);

	return query.getResultList();
	}
	else {
		TypedQuery<Contract> query = entityManager.createQuery("select * from Contract ",Contract.class);
		return query.getResultList();
	}
	
}

	
	
}
