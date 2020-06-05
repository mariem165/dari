package ManagedBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import Services.AdPurchaseService;
import Utils.FileUploadServlet;
import tn.esprit.dari.entities.PurchaseAd;

@ManagedBean(name = "purchaseBean")
@Named
@SessionScoped
public class PurchaseAdAjout implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private String image;
	private String description;
	private String location;
	private Date date;
	private int room;
	private float area;
	private MapModel emptyModel;

	private double lat;
	private double lng;
	public static tn.esprit.dari.entities.LatLng pos = new tn.esprit.dari.entities.LatLng();

	private float price;
	public static PurchaseAd IPurch = new PurchaseAd();

	private List<PurchaseAd> list;
	private PurchaseAd PurchaseAd;

	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public tn.esprit.dari.entities.LatLng getPos() {
		return pos;
	}

	public void setPos(tn.esprit.dari.entities.LatLng pos) {
		PurchaseAdAjout.pos = pos;
	}

	private Integer PurchaseIdToBeUpdated;
    private MapModel simpleModel;

	@EJB
	AdPurchaseService adPurchaseService;

	@PostConstruct
	public void init() {
		emptyModel = new DefaultMapModel();

	}
    public MapModel getSimpleModel() {
        return simpleModel;
    }
	public void onMarkerSelect(OverlaySelectEvent event) {
		Marker selectedMarker = (Marker) event.getOverlay();
		System.out.println(selectedMarker);
	}

	public void createAdsPurchase() throws IOException, ServletException {

		System.out.println("poss" + pos);
		adPurchaseService.createAdPurchase(new PurchaseAd(title, files.getFileName(), description, location, date,
				price, room, area, pos.getLat(), pos.getLng()));
		doPost();
		this.setTitle(null);
	}

	public void addMarker() {
		Marker marker = new Marker(new LatLng(lat, lng), title);
		emptyModel.addOverlay(marker);
		System.out.println(lat);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
	}

	public void onPointSelect(PointSelectEvent event) {
		System.out.println("Add marker title: " + event.getLatLng());
		LatLng latlng = event.getLatLng();
		pos.setLng(event.getLatLng().getLng());
		pos.setLat(event.getLatLng().getLat());
		emptyModel = new DefaultMapModel();
		Marker marker = new Marker(latlng, "your house");
		emptyModel.addOverlay(marker);
	}

	public String AfficherUnProduit(PurchaseAd m1) {
		String navigateTo = "null";

		this.setTitle(m1.getTitle());
		this.setImage(m1.getImage());
		this.setPrice(m1.getPrice());
		this.setDate(m1.getDate());

		this.setLocation(m1.getLocation());

		this.setDescription(m1.getDescription());
        simpleModel = new DefaultMapModel();
        LatLng coord4 = new LatLng(m1.getLat(), m1.getLng());
System.out.println(coord4);
        simpleModel.addOverlay(new Marker(coord4, "Kaleici"));

		// this.setProjects(m1.getProject());
		navigateTo = "/DetailsPurchase?faces-redirect=true";

		System.out.println("***" + m1.getId());
		return navigateTo;

	}

	public List<PurchaseAd> getByUserId() {
		list = adPurchaseService.findByUserAd();
		return list;
	}

	public void supprimer(int id) {

		adPurchaseService.deleteAdPurchase(id);
		System.out.println("delete");

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

			// AWS Access Key ID and Secret Access Key
			BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJERQSMWBUOBUGXJQ",
					"tfzn2CeRpISvoNPqXbf1wI3QhoFSlpXxQ7U04G6n");

			AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(region)
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

			// Specify the file's size
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(files.getSize());

			// Create the upload request, giving it a bucket name, subdirectory, filename,
			// input stream, and metadata
			PutObjectRequest uploadRequest = new PutObjectRequest(bucketName, subdirectory + fileName, fileInputStream,
					metadata);
			// Make it public so we can use it as a public URL on the internet
			uploadRequest.setCannedAcl(CannedAccessControlList.PublicRead);

			// Upload the file. This can take a while for big files!
			s3client.putObject(uploadRequest);

			// Create a URL using the bucket, subdirectory, and file name
			String fileUrl = "http://s3.amazonaws.com/" + bucketName + "/" + subdirectory + "/" + fileName;

		} else {
			System.out.println("error upload");
		}

	}

	public String getTitle() {
		return title;
	}

	public PurchaseAd getIPurch() {
		return IPurch;
	}

	public void setIPurch(PurchaseAd iPurch) {
		IPurch = iPurch;
	}

	public void updatePurchase() {
		adPurchaseService.updateAdPurchase(IPurch);
	}

	public String displayPurchase(PurchaseAd m1) {

		PurchaseAdAjout.IPurch.setTitle(m1.getTitle());
		PurchaseAdAjout.IPurch.setImage(m1.getImage());
		PurchaseAdAjout.IPurch.setPrice(m1.getPrice());
		PurchaseAdAjout.IPurch.setDate(m1.getDate());

		PurchaseAdAjout.IPurch.setLocation(m1.getLocation());

		PurchaseAdAjout.IPurch.setDescription(m1.getDescription());
		System.out.println();
		String navigateTo = "UpdatePurchase?faces-redirect=true";
		return navigateTo;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public AdPurchaseService getAdPurchaseService() {
		return adPurchaseService;
	}

	public void setAdPurchaseService(AdPurchaseService adPurchaseService) {
		this.adPurchaseService = adPurchaseService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PurchaseAdAjout() {
		super();
	}

	public PurchaseAd getPurchaseAd() {
		return PurchaseAd;
	}

	public void setPurchaseAd(PurchaseAd purchaseAd) {
		PurchaseAd = purchaseAd;
	}

	public List<PurchaseAd> getList() {
		return list;
	}

	public void setList(List<PurchaseAd> list) {
		this.list = list;
	}

	public Integer getPurchaseIdToBeUpdated() {
		return PurchaseIdToBeUpdated;
	}

	public void setPurchaseIdToBeUpdated(Integer purchaseIdToBeUpdated) {
		PurchaseIdToBeUpdated = purchaseIdToBeUpdated;
	}

}
