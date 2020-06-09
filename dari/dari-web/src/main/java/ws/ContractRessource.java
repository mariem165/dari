package ws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.client.core.marshallers.FormParamMarshaller;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import tn.esprit.dari.entities.Contract;

import Interfaces.IContractRemote;

@Path("cons")
public class ContractRessource {

	@EJB
	IContractRemote conBusines;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCn(Contract con) {
		conBusines.createCn(con);
		return Response.status(Status.OK).entity("ok").build();
	}


	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteCn(@PathParam("id") int id) {
		conBusines.deleteCn(id);
		return Response.status(Status.OK).entity("deleted successfuly").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("All")
	public List<Contract> findAllContracts() {

		return conBusines.findAllCns();
	}

	@PUT
	@Path("update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateCn(Contract con, @PathParam("id") int id) {
		conBusines.updateCn(con, id);
		return Response.status(Status.OK).entity("ok").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("screenshot")
	@Produces(MediaType.APPLICATION_JSON)
	public Response screenshot() {
		conBusines.screenshot();
		return Response.status(Status.OK).entity("ok").build();
	}
    
	@GET
	@Path("image-upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	
	public Response uploadFile(MultipartFormDataInput input) throws IOException {
    	System.out.println("12345");
		String UPLOADED_FILE_PATH = "C:\\Users\\ASUS\\Pictures\\up";
		// Get API input data
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

		// Get file name
		String fileName = uploadForm.get("fileName").get(0).getBodyAsString();

		// Get file data to save
		List<InputPart> inputParts = uploadForm.get("attachment");

		for (InputPart inputPart : inputParts) {
			try {
				// Use this header for extra processing if required
				@SuppressWarnings("unused")
				MultivaluedMap<String, String> header = inputPart.getHeaders();

				// convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class, null);

				byte[] bytes = IOUtils.toByteArray(inputStream);
				// constructs upload file path
				fileName = UPLOADED_FILE_PATH + fileName;
				writeFile(bytes, fileName);
				System.out.println("Success !!!!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Response.status(200).entity("Uploaded file name : " + fileName).build();
	}

	// Utility method
	private void writeFile(byte[] content, String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fop = new FileOutputStream(file);
		fop.write(content);
		fop.flush();
		fop.close();
	}
	
	
	@Path("search")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response SearchContrat(@QueryParam("first_name") String first_name,@QueryParam("start_Date") String start_Date,@QueryParam("end_Date") String end_Date,@QueryParam("last_name") String last_name,@QueryParam("email") String email) {
		if (conBusines.SearchContrat(first_name,start_Date,end_Date,last_name,email).size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(conBusines.SearchContrat(first_name,start_Date,end_Date,last_name,email), MediaType.APPLICATION_JSON).build();
	}
	/*@POST
	@Path("/image")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadImage(
	    @FormParam("file") InputStream uploadedInputStream,
	    @FormParam("file") FormParamMarshaller fileDetails) {

	  ystem.out.println(fileDetails.getFileName());

	   String uploadedFileLocation = "/Users/temp/" + fileDetails.toString();

	   // save it
	   writeToFile(uploadedInputStream, uploadedFileLocation);

	   String output = "File uploaded to : " + uploadedFileLocation}

	
	   

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
	                   String uploadedFileLocation) {
	   try {
	      OutputStream out = new FileOutputStream(new File(
	            uploadedFileLocation));
	      int read = 0;
	      byte[] bytes = new byte[1024];

	      out = new FileOutputStream(new File(uploadedFileLocation));
	      while ((read = uploadedInputStream.read(bytes)) != -1) {
	         out.write(bytes, 0, read);
	      }
	      out.flush();
	      out.close();
	   } catch (IOException e) {
	      e.printStackTrace();
	   }
	}
*/
}
