package ws;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

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
	public Response deleteCn(@PathParam("id")int id) {
		conBusines.deleteCn(id);
		return Response.status(Status.OK).entity("deleted successfuly").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("All")
	public List<Contract> findAllContracts(){
	
		return conBusines.findAllCns();
	}
	
	
	@PUT
	@Path("update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
    public Response updateCn(Contract con , @PathParam("id") int id ) {
		conBusines.updateCn(con,id);
		return Response.status(Status.OK).entity("ok").build();
	}
	
	@POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput input) throws IOException {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        // Get file data to save
        List<InputPart> inputParts = uploadForm.get("attachment");
        return Response.status(Status.NOT_FOUND)
		   	       .entity("Aucune liste n' a ete trouve"+inputParts.size())
		           .build();
    /*    for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String fileName = getFileName(header);
                // convert the uploaded file to inputstream
              InputStream inputStream = inputPart.getBody(InputStream.class, null);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                String path = System.getProperty("user.home") + File.separator + "uploads";
                File customDir = new File(path);
                if (!customDir.exists()) {
                    customDir.mkdir();
                }
                fileName = customDir.getCanonicalPath() + File.separator + fileName;
                writeFile(bytes, fileName);
                return Response.status(200).entity("Uploaded file name : " + fileName).build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        //return null;
    }
 
    @POST
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes("application/x-www-form-urlencoded")
    public Response downloadFileWithPost(@FormParam("file") String file) {
        String path = System.getProperty("user.home") + File.separator + "uploads";
        File fileDownload = new File(path + File.separator + file);
        ResponseBuilder response = Response.ok((Object) fileDownload);
        response.header("Content-Disposition", "attachment;filename=" + file);
        return response.build();
    }
 
    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileWithGet(@QueryParam("file") String file) {
 
        String path = System.getProperty("user.home") + File.separator + "uploads";
        File fileDownload = new File(path + File.separator + file);
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment;filename=" + file);
        return response.build();
    }
 
    private String getFileName(MultivaluedMap<String, String> header) {
 
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
 
        for (String filename : contentDisposition) {
 
            if ((filename.trim().startsWith("filename"))) {
 
                String[] name = filename.split("=");
 
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
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
        System.out.println("Written: " + filename);
    }
	
	
	
	
}
