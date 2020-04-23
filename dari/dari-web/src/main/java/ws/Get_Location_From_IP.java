package ws;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Interfaces.locationRemote;
import tn.esprit.dari.entities.Location_Use_Bean;;

@Path("ip")
public class Get_Location_From_IP {
	
	@EJB
	locationRemote IS;
    public Location_Use_Bean get_ip_Details(String ip) {
        String key = "916b0352abbbe26f8e3d479d062c189d9c922f980f6130f52cf8bf68b5f9f207";
        ip = ip.trim();
        Location_Use_Bean obj_Location_Use_Bean = new Location_Use_Bean();
        System.out.println("The ip adress is before " + ip + "  split");
        try {
            if (ip.contains(",")) {
                String temp_ip[] = ip.split(",");
                ip = temp_ip[1].trim();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("The ip adress is after " + ip + " split");
        URL url;
        try {
            url = new URL("https://api.ipinfodb.com/v3/ip-city/?key=" + key + "&ip=" + ip);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            String temporaray = "";
            String temp_array[] = null;
            while (null != (strTemp = br.readLine())) {
                temporaray = strTemp;
            }
            temp_array = temporaray.split(";");
            System.out.println("Str length is " + temp_array.length);
            int length = temp_array.length;
            
            if (length == 11) {
                obj_Location_Use_Bean.setIp_address(ip);
                if (temp_array[3] != null) {
                    obj_Location_Use_Bean.setCountry_code(temp_array[3]);
                }
                if (temp_array[4] != null) {
                    obj_Location_Use_Bean.setCountry(temp_array[4]);
                }
                if (temp_array[5] != null) {
                    obj_Location_Use_Bean.setState(temp_array[5]);
                }
                if (temp_array[6] != null) {
                    obj_Location_Use_Bean.setCity(temp_array[6]);
                }
                if (temp_array[7] != null) {
                    obj_Location_Use_Bean.setZip(temp_array[7]);
                }
                if (temp_array[8] != null) {
                    obj_Location_Use_Bean.setLat(temp_array[8]);
                }
                if (temp_array[9] != null) {
                    obj_Location_Use_Bean.setLon(temp_array[9]);
                }
                if (temp_array[10] != null) {
                    obj_Location_Use_Bean.setUtc_offset(temp_array[10]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_Location_Use_Bean;
    }
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ipshow")
	public Response ShowIP(String IP) {
		
		
		 
        Get_Location_From_IP obj_Get_Location_From_IP = new Get_Location_From_IP();
        Location_Use_Bean obj_Location_Use_Bean = obj_Get_Location_From_IP.get_ip_Details(IP);
        return Response.status(Status.OK).entity(obj_Location_Use_Bean ).build();
       
    }
    
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("staterank")
    public Response StateRank() {
    	
    	List<Location_Use_Bean> L=IS.CountryByRank();
    	List<String> LL= new ArrayList<>();
    	for (int i = 0; i < L.size(); i++)
    		{LL.add((L.get(i)).getState());
    		}
    	List<String> L1= new ArrayList<>();
    	Set<String> distinct = new HashSet<>(LL);
		for (String s: distinct) {
			String D = (s + ": " + Collections.frequency(LL, s));
			L1.add(D);}
    	
    	 return Response.status(Status.OK).entity(L1).build();
    }
}