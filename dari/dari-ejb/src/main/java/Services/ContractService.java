package Services;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.Stateful;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.dari.entities.Contract;
import tn.esprit.dari.entities.ContractModelType;
import Interfaces.IContractRemote;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;


@Stateful
public class ContractService implements IContractRemote {

	@PersistenceContext(unitName = "dari-ejb")
	EntityManager entityManager;

	@Override
	public void createCn (Contract con) {
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
	
	
/*public boolean screenshot2() {
		
		Webcam webcam = Webcam.getDefault();
		webcam.getLock().disable();
	
		System.out.println(webcam.isOpen());
		
		webcam.open();
		System.out.println(webcam.isOpen());

		// get image
		BufferedImage image = webcam.getImage();
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
        String filename  = "PNG_" + timeStamp + "_.png";
		// save image to PNG file
		try {
			File file = new File("C:\\Users\\ASUS\\Pictures\\Saved Pictures\\"+filename);
			ImageIO.write(image, "PNG", file);
			WebcamUtils.capture(webcam, file);
			//webcam.getImage().getProperty("test1.png");
		} catch (IOException e) {
			//webcam.close();
			
			// TODO: handle exception
		}
		return true;
		

	}
	@Override
	public boolean screenshot() {
		TimerTask task = new TimerTask() {

		    @Override
		    public void run() {
		        screenshot2();
		    }
		};

		Timer timer = new Timer();
		timer.schedule(task, new Date(), 3000);
		return true;
	}*/
	
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
		        String filename  = "PNG_" + timeStamp;
		        filename = filename.replace(":","-");
		        System.out.println(filename);
				// save image to PNG file
				try {
					File file = new File("C:\\Users\\ASUS\\Pictures\\Saved Pictures\\"+filename+".png");
					ImageIO.write(image, "PNG", file);
					WebcamUtils.capture(webcam, file);
					
					//webcam.getImage().getProperty("test1.png");
					
					
					
					
				} catch (IOException e) {
					//webcam.close();
					
					// TODO: handle exception
				}
		    }
		};

		Timer timer = new Timer();
		timer.schedule(task, new Date(), 300000);
		return true;
		

	}

	/*@Override
    public void createUser(User user) {
        user.setPassword(MD5Hash.getMD5Hash(user.getPassword()));
        String activationHashedCode = MD5Hash.getMD5Hash(user.getNtelephone()+ user.getEmail());
        user.setConfirmationToken(activationHashedCode);
        user.setCreatedAt(new Date());
        entityManager.persist(user);
    }*/

	
}
