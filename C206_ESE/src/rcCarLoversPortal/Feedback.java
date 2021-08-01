package rcCarLoversPortal;

import java.util.Date;

public class Feedback extends Buyer {

	private Date dateNow;
	private String description;

	public Feedback(String name, int phoneNo, String description) {
		super(name, phoneNo);
		dateNow = new Date();
		this.description = description;
	}

	public Date getDateNow() {
		return dateNow;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
