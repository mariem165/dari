package ManagedBeans;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException; 
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.xml.registry.infomodel.User;

import org.apache.commons.io.FilenameUtils;

import Services.MeublesServices;
import tn.esprit.dari.entities.Meubles;
@ManagedBean
@ViewScoped
@SessionScoped
public class Fileupload {

	@EJB
	MeublesServices ms;
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH!mm!ss";
	private Part uploadedFile;
	private String folder = "K:/pifinal/dari/dari/dari-web/src/main/webapp/template/assets/img-meubles/";
	private String desc;
	private String tit;
	private String pri;
	private Meubles a;
	
	///////////////////////// the random string function and report function
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 20) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	protected String getSaltStringFile() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	 public void report(String file, String user, String reporter ,Meubles M, String status) {
		 try {
			 Calendar cal = Calendar.getInstance();
			 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			 String D = sdf.format(cal.getTime());
			 File myObj = new File("K:\\pifinal\\dari\\dari\\dari-web\\src\\main\\webapp\\reports\\"+file);
			 myObj.createNewFile();
		      FileWriter myWriter = new FileWriter("K:\\pifinal\\dari\\dari\\dari-web\\src\\main\\webapp\\reports\\"+file);
		      myWriter.write("Report at : ["+D+"] done by : [" +reporter +"] on the user :["+user + "] for the post with id : ["+M.getId()+"] that has the description : ["+M.getDescription()+ "] and as title : ["+M.getTitle()+"] the status of post and user is :["+status+"]");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  }
	
	
	///////////////////////// getters and setters
	
	
	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public Meubles getA() {
		return a;
	}

	public void setA(Meubles a) {
		this.a = a;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTit() {
		return tit;
	}

	public void setTit(String tit) {
		this.tit = tit;
	}

	public String getPri() {
		return pri;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}
	
	
	
	
	
	//////////////////////////// the upload function 
	
	
	public String saveFile(){
		
		Meubles M= new Meubles();
		M.setUser(null);
		M.setTitle(this.tit);
		M.setDescription(this.desc);
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		//////////////
		String [] WordList= { "anal"," anus"," arse"," ass "," ballsack"," balls"," bastard"," bitch"," biatch"," bloody"," blowjob"," blow"," job"," bollock"," bollok"," boner"," boob"," bugger"," bum"," butt"," buttplug"," clitoris"," cock"," coon"," crap"," cunt"," damn"," dick"," dildo"," dyke"," fag"," feck"," fellate"," fellatio"," felching"," fuck"," f u c k"," fudgepacker"," fudge"," packer"," flange","goddamn","god damn"," hell"," homo"," jerk"," jizz"," knobend"," knob"," end"," labia"," lmao"," lmfao"," muff"," nigger"," nigga"," omg"," penis"," piss"," poop"," prick"," pube"," pussy"," queer"," scrotum"," sex"," shit"," slut"," smegma"," spunk"," tit"," tosser"," turd"," twat"," vagina"," wank"," whore"," wtf"};	
	    for (String word : WordList) {
	        if (this.tit.contains(word)) {
	        	Calendar cal = Calendar.getInstance();
				 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				 String D = sdf.format(cal.getTime());
	        	report (D+"-"+getSaltStringFile()+".txt",null,"system restriction",M,"banned");
	            return "banned?faces-redirect=true";
	        }
	        if (this.desc.contains(word)) {
	        	Calendar cal = Calendar.getInstance();
				 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				 String D = sdf.format(cal.getTime());
	        	report (D+"-"+getSaltString()+".txt",null,null,M,"banned");
	            return "banned?faces-redirect=true";
	        }
	        
	    }

		////////////////
		
	M.setUser(null);
		String F=params.get("tentacles");
		M.setPrix(F);

		try (InputStream input = uploadedFile.getInputStream()) {
			String fileName = uploadedFile.getSubmittedFileName();
	        Files.copy(input, new File(folder, fileName).toPath());
	        File f1 = new File(folder+fileName);
	        String ext1 = FilenameUtils.getExtension(folder+fileName);
	        String named = getSaltString();
	        File f2 = new File(folder+named+"."+ext1);
	        M.setImage(named+"."+ext1);
	        boolean b = f1.renameTo(f2);
	        
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	
	ms.CreateMeubles(M);
	
	return "meubles?faces-redirect=true";
	}


	
}
