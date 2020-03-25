package ws;



import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.*;

import Interfaces.IUserRemote;

import tn.esprit.dari.entities.User;


@Path("user")
public class UserResource {

	@EJB
	IUserRemote userBusiness;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("register")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
       userBusiness.createUser(user);
		return Response.status(Status.OK).entity("ok").build();
	 
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("confirm/{confirmationToken}")
	public Response activateAccount(@PathParam("confirmationToken") String confirmationToken) {
		boolean result = userBusiness.activateAccount(confirmationToken);
		if (result) {
			return Response.status(Status.OK).entity(true).build();
		} else {
			return Response.status(Status.FORBIDDEN).entity(false).build();
		}

	}
	@PUT
	@Path("update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateUser(User user, @PathParam(value = "id") int id) {

		userBusiness.updateUser(user);
		return Response.status(Status.OK).entity("ok").build();

	}
	
	@DELETE
	@Path("delete/{id}")
	public void deleteUser(@PathParam("id") int id) {

		userBusiness.deleteUser(id);

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("All")
	public List<User> findAllUsers() {

		return userBusiness.findAllUsers();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findUser/{id}")
	public User findUserById(@PathParam("id") int id) {

		return userBusiness.findUserById(id);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{oldPwd}/{newPwd}")
	public Response changePwd(User user, @PathParam("oldPwd") String oldPwd, @PathParam("newPwd") String newPwd) {
		if (userBusiness.changePwd(user, oldPwd, newPwd))
			return Response.status(Status.OK).entity(true).build();
		else {
			return Response.status(Status.FORBIDDEN).entity(false).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("assignAdmin/{id}")
	public Response AddAgent(@PathParam("id") int id) {

	
		return Response.status(Status.FOUND).entity(userBusiness.AssignAdmin(id)).build();

	}
	
}
