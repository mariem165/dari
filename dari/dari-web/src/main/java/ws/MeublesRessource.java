package ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Interfaces.IMeublesRemote;
import tn.esprit.dari.entities.Meubles;

@Path("meubles")
public class MeublesRessource {
@EJB
IMeublesRemote MM;
//////////////////////////////
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Path("MeublesAdd")
public Response CreateMeuble (Meubles M ) {
	MM.CreateMeubles(M);
	return Response.status(Status.OK).entity("ok").build();
	
}
////////////////////////
@DELETE
@Consumes(MediaType.APPLICATION_JSON)
@Path("MeublesRemove")
public Response RemoveMeubles (Meubles M ) {
	MM.DeleteMeubles(M);
	return Response.status(Status.OK).entity("ok").build();
	
}
//////////////////////
@PUT
@Consumes(MediaType.APPLICATION_JSON)
@Path("MeublesUpdate")
public Response UpdateMeubles (@QueryParam("id")int id, Meubles m2 ) {
	MM.UpdateMeuble(id, m2);
	return Response.status(Status.OK).entity("ok").build();}
//////////////////////
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("MeublesShow")
public Response ShowMeubles() {
	List<Meubles> list=MM.ShowMeubles();
	
	return Response.status(Status.OK).entity(list).build();}
	
/////////////////////
}