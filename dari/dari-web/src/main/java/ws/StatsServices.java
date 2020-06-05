package ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import Interfaces.statisticsRemote;
import tn.esprit.dari.entities.Meubles;

@Path("Statistiques")
public class StatsServices {
@EJB
statisticsRemote SR;

//////
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("topDate")
public Response TopDatesWS() {
	List<String> list=SR.TOP5Dates();
	return Response.status(Status.OK).entity(list).build();
	
}

@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("topIP")
public Response TopIPWS() {
	List<String> list=SR.TOP5Ip();
	return Response.status(Status.OK).entity(list).build();
	
}
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("topLocation")
public Response TopLocationWS() {
	List<String> list=SR.TOP5Location();
	return Response.status(Status.OK).entity(list).build();
	
}
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("topAd")
public Response TopAdWS() {
	List<Meubles> list=SR.TOP5Ad();
	return Response.status(Status.OK).entity(list).build();
	
}

}
