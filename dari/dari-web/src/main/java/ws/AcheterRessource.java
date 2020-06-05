package ws;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Interfaces.IAcheterRemote;
import tn.esprit.dari.entities.History;



@Path("achat")
public class AcheterRessource {

	
	@EJB
	IAcheterRemote achatBusniss;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("history/{IdAd}/{idUser}")
	public History saveHistory(@PathParam("IdAd") int IdAd,@PathParam("IdAd") int idUser) {

		return achatBusniss.SaveAds(IdAd, idUser);
	}
	
}
