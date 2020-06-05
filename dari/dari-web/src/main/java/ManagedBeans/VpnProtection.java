package ManagedBeans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class VpnProtection {
	public static String RED2;
	private String RED;
	
	
	
	
	 public static String getRED2() {
		return RED2;
	}




	public static void setRED2(String rED2) {
		RED2 = rED2;
	}




	public String getRED() {
		return VpnProtection.RED2;
	}




	public void setRED(String rED) {
		RED = rED;
	}




	public void onPageLoad(){
		    List <String> L= Arrays.asList(
		    		"1.0.69.27",
		    		"1.236.132.203",
		    		"1.242.79.148",
		    		"2.58.12.12",
		    		"2.58.12.18",
		    		"2.58.12.24",
		    		"2.58.12.30",
		    		"2.58.12.36",
		    		"2.58.12.42",
		    		"2.58.12.48",
		    		"2.58.12.54"
		    		
		    		
		    		);
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
		    
		    if (L.contains(systemipaddress)) {System.out.println("vpn detected"); VpnProtection.RED2="test.jsf";}
		    else VpnProtection.RED2="meubles.jsf";
		    
		   
		 }
}
