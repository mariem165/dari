package ws;



import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.*;

import Interfaces.IUserRemote;
import Services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.dari.entities.AccountState;
import tn.esprit.dari.entities.User;
import tn.esprit.dari.entities.UserType;


@Path("user")
public class UserResource {

	@EJB
	IUserRemote userBusiness;
	@Context
	private UriInfo uriInfo;
	
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
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{oldPwd}/{newPwd}")
	public Response changePwd(@PathParam("oldPwd") String oldPwd, @PathParam("newPwd") String newPwd) {
		if (userBusiness.changePwd(oldPwd, newPwd))
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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findByMail/{mail}")
	public User findByMail(@PathParam("mail") String mail) {

		return userBusiness.findByMail(mail);
	}
	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("email") String email, @FormParam("password") String password) {

		try {
			User u = authenticate(email, password);
			UserType aRole = u.getUsertype();
			if (u != null) {
				if (u.getAccountState() != AccountState.ACTIVATED)
					return Response.status(Response.Status.NOT_ACCEPTABLE)
							.entity("Your account is inactive , Please check your Email and confirm your Account !")
							.build();
				else {
					String role = null;
					if (u.getUsertype() == UserType.admin) {
						role = "admin";
					} else if (u.getUsertype() == UserType.bayer) {
						role = "bayer";
					}else if (u.getUsertype() == UserType.owner) {
						role = "owner";
					} else if (u.getUsertype() == UserType.renter) {
						role = "renter";
					} 
					System.out.println("++++++++++++++++");
					String token = issueToken(Integer.toString(u.getId()), role);
					System.out.println("--------------------------------");
					System.out.println("token : " + token);
					//org.json.JSONObject obj = new org.json.JSONObject();
					//obj.put("status", true);
					//obj.put("token", token);
					//obj.put("userId", u.getId());
					//obj.put("role", role);
					return Response.status(Response.Status.ACCEPTED).entity("Token : " +token).build();
				}
			}
			return Response.status(Response.Status.FORBIDDEN).entity("please enter mail and password !").build();
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	private User authenticate(String username, String password) {
		User u = userBusiness.loginUser(username, password);
		return u;
	}

	private String issueToken(String username, String role) {
		String keyString = "simplekey";
		System.out.println("1111111111111");
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		System.out.println("22222222222222"+uriInfo.getAbsolutePath().toString());
		String jwtToken = Jwts.builder().setSubject(username).claim("Role", role)
				.setIssuer(uriInfo.getAbsolutePath().toString()).setIssuedAt(new Date())
				.setExpiration(toDate(LocalDateTime.now().plusMinutes(15L))).signWith(SignatureAlgorithm.HS512, key)
				.compact();
		
		return jwtToken;
	}

	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("logout")
	public Response logout() {

		UserService.UserLogged = null;
		issueToken(null, null);
		return Response.status(Status.NOT_FOUND).entity(false).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("userNumber")
	public int findUserNumber() {

		return userBusiness.getNombreUser();
	}
}
