package ManagedBeans;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.maxmind.geoip2.*;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import Services.CommentService;
import Services.MeublesServices;
import Services.StatService;

import tn.esprit.dari.entities.Comment;
import tn.esprit.dari.entities.Meubles;
import tn.esprit.dari.entities.Statistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

@ManagedBean (name="MeubleBean")
@SessionScoped 
@RequestScoped

public class MeubleBean implements Serializable {
@EJB
MeublesServices ms;

@EJB
StatService Ss;

public List<Meubles> list;
public List<Comment> listcom;
public String S="";
public String url;
private Meubles Meu;
public static Meubles MMM;
public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH!mm!ss";
public static final String DATE_FORMAT_NOW2 = "yyyy/MM/dd";








public Meubles getMeu() {
	return Meu;
}

public void setMeu(Meubles meu) {
	Meu = meu;
}

///////////////////////
public String getUrl() {
	return url;
}


////////////////////////

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

 public String reportButton() throws IOException {
	 
	 try {
		 Calendar cal = Calendar.getInstance();
		 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		 String D = sdf.format(cal.getTime());
		 File myObj = new File("K:\\pifinal\\dari\\dari\\dari-web\\src\\main\\webapp\\reports\\"+D+"-"+getSaltStringFile()+".txt");
		 myObj.createNewFile();
	      FileWriter myWriter = new FileWriter("K:\\pifinal\\dari\\dari\\dari-web\\src\\main\\webapp\\reports\\"+D+"-"+getSaltStringFile()+".txt");
	      myWriter.write("Report at : ["+D+"] done by : [" +"salah123"+"] on the user :["+"mourad522" + "] for the post that has the description : ["+MeubleBean.MMM.getDescription()+ "] and as title : ["+MeubleBean.MMM.getTitle()+"] the status of post and user is :["+"on hold"+"]");
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	 /*HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	 String ipAddress = request.getHeader("X-FORWARDED-FOR");
	 if (ipAddress == null) {
	     ipAddress = request.getRemoteAddr();
	 }
	 System.out.println("ipAddress:" + ipAddress);*/

	 
	 
	 return "reported?faces-redirect=true";
	 
	 
	  }




//////////////////////

public String setUrl(String end) throws Exception {
	/*HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String URL = request.getRequestURL().toString();*/
	//String URL="meubles2?id="+end;
    this.url=end;
    Meubles Meub= new Meubles();
    /*this.setMeu(end);*/
    Meub.setId(Integer.parseInt(end));
    Meub.setDescription(ShowDesc(end));
    Meub.setImage(ShowImg(end));
    Meub.setPrix(ShowPrix(end));
    Meub.setTitle(ShowName(end));
    this.Meu=Meub;
    System.out.println(this.Meu.getImage());
    System.out.println(this.Meu.getImage());
    MeubleBean.MMM=Meub;
    
    Statistics S=new Statistics();
    S.setMeubles(MeubleBean.MMM);
   
    
    
 ///////////////////////////
    InetAddress localhost = InetAddress.getLocalHost(); 
    System.out.println("System IP Address : " + 
                  (localhost.getHostAddress()).trim()); 

    // Find public IP address 
    String systemipaddress = ""; 
    try
    { 
        URL url_name = new URL("http://bot.whatismyipaddress.com"); 

        BufferedReader sc = 
        new BufferedReader(new InputStreamReader(url_name.openStream())); 

        // reads system IPAddress 
        systemipaddress = sc.readLine().trim(); 
    } 
    catch (Exception e) 
    { 
        systemipaddress = "Cannot Execute Properly"; 
    } 
    S.setIp(systemipaddress);
 ///////////////////////////
    File database = new File("K:\\pifinal\\dari\\dari\\dari-web\\src\\main\\webapp\\databases\\GeoLite2-Country.mmdb");
    File database2= new File("K:\\pifinal\\dari\\dari\\dari-web\\src\\main\\webapp\\databases\\GeoLite2-City.mmdb");
    
    DatabaseReader reader = new DatabaseReader.Builder(database).build();
    DatabaseReader reader2 = new DatabaseReader.Builder(database2).build();

        InetAddress ipAddress = InetAddress.getByName(systemipaddress);
        
        CityResponse response2 = reader2.city(ipAddress);

        CountryResponse response = reader.country(ipAddress);
        
        City city = response2.getCity();
        Country country = response.getCountry();
    
 ///////////////////////////
        Calendar cal = Calendar.getInstance();
		 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW2);
		 String D = sdf.format(cal.getTime());
		 S.setDate(D);
    S.setLocation(country.getName()+", "+city.getName());
    S.setTime(Calendar.getInstance().getTime());
    Ss.CreateStat(S);
    return "meubles2";
}
//////////////////////

public String ShowPrix (String URL)
{
	for (Meubles Me : ms.ShowMeubles()) {
		if (Me.getId()==Integer.parseInt(URL)) return Me.getPrix();
	}
	return null;
	
}




public String ShowName (String URL)
{
	for (Meubles Me : ms.ShowMeubles()) {
		if (Me.getId()==Integer.parseInt(URL)) return Me.getTitle();
	}
	return null;
	
}
public String ShowDesc (String URL)
{
	for (Meubles Me : ms.ShowMeubles()) {
		if (Me.getId()==Integer.parseInt(URL)) return Me.getDescription();
	}
	return null;
	
	
}
public String ShowImg (String URL)
{
	for (Meubles Me : ms.ShowMeubles()) {
		if (Me.getId()==Integer.parseInt(URL)) return Me.getImage();
	}
	return null;
	
}

public List<Meubles> getList() {

	if (S.equals("")==true) return ms.ShowMeubles();
	else {
		List<Meubles> K = new ArrayList<>();
		for (Meubles Me : ms.ShowMeubles()) {
			if (Me.getDescription().contains(S)) K.add(Me);
			else {if (Me.getTitle().contains(S)) K.add(Me);}
			
			}
		return K;
	}
		 
}

public void setList(List<Meubles> list) {
	this.list = list;
}



public String getS() {
	return S;
}

public String setS() {
	FacesContext fc = FacesContext.getCurrentInstance();
	Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
	String F=params.get("reshbut");
	this.S=F;
	return ("meuble");
	
}
///////////////// comment section

public Comment a;
@EJB
CommentService Cs;


private String content;

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}
public String PostComment()
{

Comment C= new Comment();
C.setMeubles(MeubleBean.MMM);
C.setTime(Calendar.getInstance().getTime());
C.setUser(null);
C.setContent(this.content);
Cs.CreateComment(C);


///////////////
List<Comment> List1 = new ArrayList<>();
for (Comment Me : Cs.ShowComment()) {
	if (Me.getId()==MeubleBean.MMM.getId()) List1.add(Me);
	System.out.println(MeubleBean.MMM.getId());
	System.out.println(Me.getId());
	System.out.println(Me.getId()==MeubleBean.MMM.getId());
	
	}
///////////////
return "meubles2";
}

public List<Comment> getListcom() {
	List<Comment> List1 = new ArrayList<>();
	for (Comment Me : Cs.ShowComment()) {
		if (Me.getMeubles().getId()==MeubleBean.MMM.getId()) List1.add(Me);
		
		}
	return List1;
}

public void setListcom(List<Comment> listcom) {
	this.listcom = listcom;
}



}
