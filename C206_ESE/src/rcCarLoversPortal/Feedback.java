package rcCarLoversPortal;

import java.util.Date;

public class Feedback extends Buyer {

	private Date dateNow;
	private String description;
	private boolean status;

	public Feedback(String name, int phoneNo, String description) {
		super(name, phoneNo);
		dateNow = new Date();
		this.description = description;
		status = false;
	}

	public Date getDateNow() {
		return dateNow;
	}

	public String getDescription() {
		return description;
	}

	public boolean getStatus() {
		return status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
