package ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Interfaces.IAdPurchaseRemote;
import Services.UserService;
import tn.esprit.dari.entities.PurchaseAd;
import tn.esprit.dari.entities.User;

@Path("adPurchase")
public class AdPurchaseRessource {

	@EJB
	IAdPurchaseRemote adPurchaseBusines;
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("ajout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAdPurchase(PurchaseAd ad) {
		ad.setUser(UserService.UserLogged);
		adPurchaseBusines.createAdPurchase(ad);
		return Response.status(Status.OK).entity("ok").build();
	}
	
	@DELETE
	@Path("delete/{id}")
	public void deleteAdPurchase(@PathParam("id")int id) {
		adPurchaseBusines.deleteAdPurchase(id);
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("All")
	public List<PurchaseAd> findAllAdsPurchase(){
	
		return adPurchaseBusines.findAllAdsPurchase();
	}
	
	@PUT
	@Path("update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
    public Response updateAdPurchase(PurchaseAd ad , @PathParam(value = "id") int id ) {
		adPurchaseBusines.updateAdPurchase(ad);
		return Response.status(Status.OK).entity("ok").build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findByTitle/{title}")
	public PurchaseAd findByTitle(@PathParam("title") String title) {

		return adPurchaseBusines.findByTitle(title);
	}
}
