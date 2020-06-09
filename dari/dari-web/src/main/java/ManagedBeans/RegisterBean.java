package ManagedBeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;

import org.primefaces.model.file.UploadedFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import Services.UserService;
import tn.esprit.dari.entities.User;
import tn.esprit.dari.entities.UserType;

@ManagedBean(name = "registerBean")
@SessionScoped
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String first_name;

	private String last_name;
	private String email;
	private String password;

	private String address;
	private String Ntelephone;
	private UserType usertype;
	private String profileImage;
	private User user;

	@EJB
	UserService userService;

	public void addUser() {
		userService.createUser(
				new User(first_name, last_name, email, password, address, Ntelephone, usertype, "image.jpg"));
	}

	private UploadedFile files;

	public UploadedFile getFiles() {
		return files;
	}

	public void setFiles(UploadedFile files) {
		this.files = files;
	}

	public void doPost() throws IOException, ServletException {

		System.out.println(files.getFileName());

		System.out.println(files);
		String fileName = files.getFileName();

		if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {

			InputStream fileInputStream = files.getInputStream();

			String region = "us-east-1";
			String bucketName = "mariempidev";
			String subdirectory = "images/";

			BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJERQSMWBUOBUGXJQ",
					"tfzn2CeRpISvoNPqXbf1wI3QhoFSlpXxQ7U04G6n");

			AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(region)
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(files.getSize());
			PutObjectRequest uploadRequest = new PutObjectRequest(bucketName, subdirectory + fileName, fileInputStream,
					metadata);
			uploadRequest.setCannedAcl(CannedAccessControlList.PublicRead);

			s3client.putObject(uploadRequest);
			String fileUrl = "http://s3.amazonaws.com/" + bucketName + "/" + subdirectory + "/" + fileName;

		} else {
			System.out.println("error upload");
		}

	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNtelephone() {
		return Ntelephone;
	}

	public void setNtelephone(String ntelephone) {
		Ntelephone = ntelephone;
	}

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public RegisterBean() {
		super();
	}

}
