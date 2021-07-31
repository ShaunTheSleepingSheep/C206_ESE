package rcCarLoversPortal;

public class Buyer {
	
	private String name;
	private int phoneNo;

	public Buyer(String name, int phoneNo) {
		this.name = name;
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
}
