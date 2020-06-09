package managedBean;


	
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException; 
	
	import java.io.InputStream;
	import java.nio.file.Files;
	import java.text.SimpleDateFormat;
	import java.util.Calendar;
import java.util.Date;
import java.util.Map;
	import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
	import javax.faces.bean.ManagedBean;
	import javax.faces.bean.SessionScoped;
	import javax.faces.bean.ViewScoped;
	import javax.faces.context.FacesContext;
	import javax.servlet.http.Part;
	

	import org.apache.commons.io.FilenameUtils;

import Interfaces.IContractRemote;
import Interfaces.IUserRemote;
import Services.ContractService;
import tn.esprit.dari.entities.Contract;
import tn.esprit.dari.entities.ContractModelType;
import tn.esprit.dari.entities.Meubles;
import tn.esprit.dari.entities.User;
	@ManagedBean
	//@ViewScoped
	@SessionScoped
	public class uploadImageC {
		
	//	MeublesServices ms;
		@EJB
		IContractRemote cs;
		@EJB
		IUserRemote userService;
		public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH!mm!ss";
		private Part uploadedFile;
		private String folder = "C:/Users/ASUS/Documents/dari/dari/dari-web/src/main/webapp/template/assets/img_Contract/";
	//	private String folder = System.getProperty("jboss.home.dir")+"\\standalone\\deployments\\dari-ear.ear\\dari-web.war\\ContratImage";

		
		private String desc;
		private String tit;
		private String pri;
		private Meubles a;
		private Contract contract;
		private Date start_Date;
		private Date end_Date;
		ContractModelType typeContract;
		public ContractModelType getTypeContract() {
			return typeContract;
		}

		public void setTypeContract(ContractModelType typeContract) {
			this.typeContract = typeContract;
		}

		public Contract getContract() {
			return contract;
		}

		@PostConstruct
		public void init() {
			contract = new Contract();
		//	this.getAllContract();
			//this.lstUser = userService.findAllUsers();
		}
		public void setContract(Contract contract) {
			this.contract = contract;
		}





		
		
		
		public Date getStart_Date() {
			return start_Date;
		}

		public void setStart_Date(Date start_Date) {
			this.start_Date = start_Date;
		}

		
		public Date getEnd_Date() {
			return end_Date;
		}

		public void setEnd_Date(Date end_Date) {
			this.end_Date = end_Date;
		}

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
		
		 public void report(String file, String user, String reporter ,Contract M, String status) {
			 try {
				 Calendar cal = Calendar.getInstance();
				 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				 String D = sdf.format(cal.getTime());
				 File myObj = new File("C:\\Users\\ASUS\\Documents\\dari\\dari\\dari-web\\src\\main\\webapp\\ContratImage\\"+file);
				 myObj.createNewFile();
			      FileWriter myWriter = new FileWriter("C:\\Users\\ASUS\\Documents\\dari\\dari\\dari-web\\src\\main\\webapp\\ContratImage\\"+file);
			      myWriter.write("Report at : ["+D+"] done by : [" +reporter +"] on the user :["+user + "] for the post with id : ["+M.getId()+"] that has a start date : ["+M.getStart_Date()+ "] and EndDate : ["+M.getEnd_Date()+ "] the status of post and user is :["+status+"]");
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
			
		
			Contract M =new Contract();
			User u=userService.findUserById(1);
			M.setUser(u);
			M.setStart_Date(start_Date);
			M.setEnd_Date(end_Date);
			M.setContract_model_type(typeContract);
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
			//////////////
			/*String [] WordList= { "anal"," anus"," arse"," ass "," ballsack"," balls"," bastard"," bitch"," biatch"," bloody"," blowjob"," blow"," job"," bollock"," bollok"," boner"," boob"," bugger"," bum"," butt"," buttplug"," clitoris"," cock"," coon"," crap"," cunt"," damn"," dick"," dildo"," dyke"," fag"," feck"," fellate"," fellatio"," felching"," fuck"," f u c k"," fudgepacker"," fudge"," packer"," flange","goddamn","god damn"," hell"," homo"," jerk"," jizz"," knobend"," knob"," end"," labia"," lmao"," lmfao"," muff"," nigger"," nigga"," omg"," penis"," piss"," poop"," prick"," pube"," pussy"," queer"," scrotum"," sex"," shit"," slut"," smegma"," spunk"," tit"," tosser"," turd"," twat"," vagina"," wank"," whore"," wtf"};	
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
		        
		    }*/

			////////////////
			
		   
			//String F=params.get("tentacles");
			//M.setPrix(F);

			try (InputStream input = uploadedFile.getInputStream()) {
				String fileName = uploadedFile.getSubmittedFileName();
				System.out.println(""+fileName);
				 String ext1 = FilenameUtils.getBaseName(fileName);
				 String ext2 = FilenameUtils.getExtension(folder+fileName);
		        Files.copy(input, new File(folder, ext1+"."+ext2).toPath());
		        File f1 = new File(folder+fileName);
		        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+fileName );
		  //      String ext1 = FilenameUtils.getBaseName(fileName);
		     //   String ext2 = FilenameUtils.getExtension(folder+fileName);
		      System.out.println("haaaaaaaaaaaaannnnni hnnnanananana"+ext1);
		        String named = getSaltStringFile();
		        System.out.println("haaaaaaaaaaaaannnnni hnnnanananana"+named);
		        File f2 = new File("."+ext1);
		        System.out.println(""+ext1);
				  
		        M.setImage(ext1+"."+ext2);
		        
		        boolean b = f1.renameTo(f2);
		        
		    }
		    catch (IOException e) {
		        e.printStackTrace();
		    }
		
	//	ms.CreateMeubles(M);
		cs.createCn(M);
		return "AllContract?faces-redirect=true";
		}


		
	}


