package rcCarLoversPortal;

import java.util.Date;

public class Appointment extends Buyer {
	
	private Date date;

	public Appointment(String name, int phoneNo, Date date) {
		super(name, phoneNo);
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
