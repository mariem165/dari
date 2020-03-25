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

import Interfaces.ICommentRemote;
import tn.esprit.dari.entities.Comment;

@Path("Comment")
public class CommentService {
	@EJB
	ICommentRemote MM;
	//////////////////////////////
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("CommentAdd")
	public Response CreateComment (Comment M ) {
		MM.CreateComment(M);
		return Response.status(Status.OK).entity("ok").build();
		
	}
	////////////////////////
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("CommentRemove")
	public Response RemoveComment (Comment M ) {
		MM.DeleteComment(M);
		return Response.status(Status.OK).entity("ok").build();
		
	}
	//////////////////////
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("CommentUpdate")
	public Response UpdateComment (@QueryParam("id")int id, Comment m2 ) {
		MM.UpdateComment(id, m2);
		return Response.status(Status.OK).entity("ok").build();}
	//////////////////////
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("CommentShow")
	public Response ShowComment() {
		List<Comment> list=MM.ShowComment();
		
		return Response.status(Status.OK).entity(list).build();}
		
	/////////////////////

}
