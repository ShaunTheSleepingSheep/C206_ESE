package rcCarLoversPortal;

import java.util.Date;

public class Feedback extends Buyer {

	private Date dateCreated;
	private String description;

	public Feedback(String name, int phoneNo, String description) {
		super(name, phoneNo);
		dateCreated = new Date();
		this.description = description;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
