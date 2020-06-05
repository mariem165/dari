package Utils;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//get the file chosen by the user
		Part filePart = request.getPart("fileToUpload");
		String fileName = filePart.getSubmittedFileName();
		
		if(fileName.endsWith(".jpg") || fileName.endsWith(".png")){

		    InputStream fileInputStream = filePart.getInputStream();
		    
		    String accessKeyId = "ASIA5AAQPH7DJ2J6MFPY";
		    String secretAccessKey =  "9DPaEVeuVCUYqi1b+ozGbaFJqQUo34Mi+iKn0uPp";
		    String region = "US";
		    String bucketName = "mariempidev";
		    String subdirectory = "images/";
		    
		    //AWS Access Key ID and Secret Access Key
		    BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);
		   
		    //This class connects to AWS S3 for us
		    AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(region)
		    		.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
		    
		    //Specify the file's size
		    ObjectMetadata metadata = new ObjectMetadata();
		    metadata.setContentLength(filePart.getSize());

		    //Create the upload request, giving it a bucket name, subdirectory, filename, input stream, and metadata
		    PutObjectRequest uploadRequest = new PutObjectRequest(bucketName, subdirectory + fileName, fileInputStream, metadata);
		    //Make it public so we can use it as a public URL on the internet
		    uploadRequest.setCannedAcl(CannedAccessControlList.PublicRead);
		    
		    //Upload the file. This can take a while for big files!
		    s3client.putObject(uploadRequest);
		    
			//Create a URL using the bucket, subdirectory, and file name
			String fileUrl = "http://s3.amazonaws.com/" + bucketName + "/" + subdirectory + "/" + fileName;			
			
			//We can still get other data from the form
			String name = request.getParameter("name");
			
			response.getOutputStream().println("<p>Thanks " + name + "! Here's the image you uploaded:</p>");
			response.getOutputStream().println("<img src=\"" + fileUrl + "\" />");
			response.getOutputStream().println("<p>Upload another image <a href=\"http://localhost:8080/index.html\">here</a>.</p>");	
		}
		else{
			//the file was not a JPG or PNG
			response.getOutputStream().println("<p>Please only upload JPG or PNG files.</p>");
			response.getOutputStream().println("<p>Upload another file <a href=\"http://localhost:8080/index.html\">here</a>.</p>");	
		}
	}
}
