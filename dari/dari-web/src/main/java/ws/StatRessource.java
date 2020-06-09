package ws;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import Interfaces.IContractRemote;
import Interfaces.IStatRemote;
import tn.esprit.dari.entities.Contract;

@Path("stat")
public class StatRessource {
	@EJB
	IStatRemote statBusines;

	@GET
	@Path("contrat")
	@Produces(MediaType.APPLICATION_JSON)
	public Response contractTypeStat() {
		return Response.ok(statBusines.contractTypeStat(), MediaType.APPLICATION_JSON).build();
	}
}
