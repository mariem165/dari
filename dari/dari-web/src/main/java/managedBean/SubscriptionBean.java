package managedBean;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;

import Interfaces.IContractRemote;
import Interfaces.ISubscriptionRemote;
import Interfaces.IUserRemote;
import Services.InsurenceServiceLocal;
import Services.subscriptionService;
import tn.esprit.dari.entities.Insurence;
import tn.esprit.dari.entities.Subscription;
import tn.esprit.dari.entities.User;

@ManagedBean(name = "subscriptionBean")
@SessionScoped
public class SubscriptionBean implements Serializable {

	@EJB
	ISubscriptionRemote subscriptionService;
	@EJB
	InsurenceServiceLocal insurenceService;
	@EJB
	IUserRemote userService;
	User user;
	List<Insurence> lstInsurence;
	Insurence insurence;
	List<Subscription> listSub;
	Subscription subscription = new Subscription();
	List<Subscription> listSubs;

	Webcam webcam = Webcam.getDefault();

	@PostConstruct
	public void init() {
		user = userService.findUserById(1);
		this.lstInsurence = insurenceService.getall();
		if (this.lstInsurence == null)
			this.lstInsurence = new ArrayList<Insurence>();

	}

	public boolean exit(Insurence in) {
		for (Subscription subscription : user.getSubscription()) {
			if (subscription.getInsurencee().getId() == in.getId())
				return true;

		}
		return false;
	}

	public boolean stopScreenshot() {
		
		
		
		if (webcam.isOpen()) {
			webcam.getLock().disable();
		        webcam.close();
		        
		    }
		//webcam.open();
		
		return true;
		
		
		/*Webcam webcam2 = Webcam.getDefault();
		webcam2.close();
		return true;*/
	}
	

	public List<Subscription> getListSubs() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		User emp = (User) sessionMap.get("emp");
		
		System.out.println("li est "+emp.getId());
		return listSubs=subscriptionService.findSubcription(emp.getId());
		
	}
	

	public void setListSubs(List<Subscription> listSubs) {
		this.listSubs = listSubs;
	}

	public boolean screenshot() {

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				webcam.getLock().disable();
				webcam.open();

				// get image
				BufferedImage image = webcam.getImage();
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
				String filename = "PNG_" + timeStamp;
				filename = filename.replace(":", "-");
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

	public List<Insurence> getLstInsurence() {
		return lstInsurence;
	}

	public void setLstInsurence(List<Insurence> lstInsurence) {
		this.lstInsurence = lstInsurence;
	}

	public Insurence getInsurence() {
		return insurence;
	}

	public void setInsurence(Insurence insurence) {
		this.insurence = insurence;
	}

	public String addInsurence(Insurence ins) {
		this.insurence = ins;
		return "AddAbonnement?faces-redirect=true";
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public void addSub() {
		this.subscription.setInsurencee(this.insurence);
		System.out.println("idddddddddd" + this.insurence.getId());
		this.subscription.setUser(user);

		subscriptionService.createSub(subscription);

		subscription = new Subscription();
		insurence = new Insurence();
	}

	public List<Subscription> getListSub() {
		return subscriptionService.findAllSubs();
	}

	public void setListSub(List<Subscription> listSub) {
		this.listSub = listSub;
	}

}
