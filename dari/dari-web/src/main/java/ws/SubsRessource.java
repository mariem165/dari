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

import tn.esprit.dari.entities.Subscription;
import Interfaces.ISubscriptionRemote;

@Path("subs")
public class SubsRessource {

	@EJB
	ISubscriptionRemote subBusines;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createSub(Subscription sub) {
		subBusines.createSub(sub);
		return Response.status(Status.OK).entity("ok").build();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteSub(@PathParam("id")int id) {
		subBusines.deleteSub(id);
		return Response.status(Status.OK).entity("deleted successfuly").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("All")
	public List<Subscription> findAllSubscriptions(){
	
		return subBusines.findAllSubs();
	}
	
	
	@PUT
	@Path("update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
    public Response updateSub(Subscription sub , @PathParam("id") int id ) {
		subBusines.updateSub(sub,id);
		return Response.status(Status.OK).entity("ok").build();
	}
}
