package rcCarLoversPortal;

import java.util.Date;

public class Feedback extends Buyer {

	private Date dateCreated;
	private String description;
	private String status;

	public Feedback(String name, int phoneNo, String description) {
		super(name, phoneNo);
		dateCreated = new Date();
		this.description = description;
		status = "PENDING";
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
