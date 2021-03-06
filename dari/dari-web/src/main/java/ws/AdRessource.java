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

import tn.esprit.dari.entities.Ad;
import Interfaces.IAdRemote;

@Path("ad")
public class AdRessource {
	@EJB
	IAdRemote adBusines;
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("ajout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAd(Ad ad) {
		adBusines.createAd(ad);
		return Response.status(Status.OK).entity("ok").build();
	}
	
	@DELETE
	@Path("delete/{id}")
	public void deleteAd(@PathParam("id")int id) {
		adBusines.deleteAd(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("All")
	public List<Ad> findAllAds(){
	
		return adBusines.findAllAds();
	}
	
	
	@PUT
	@Path("update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
    public Response updateAd(Ad ad , @PathParam(value = "id") int id ) {
		adBusines.updateAd(ad);
		return Response.status(Status.OK).entity("ok").build();
	}
	

}
