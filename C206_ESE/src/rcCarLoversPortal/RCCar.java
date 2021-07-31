package rcCarLoversPortal;

public class RCCar extends Item {
	
	private int speed;
	private String driveTrain;
	private String body;

	public RCCar(String assetID, String assetName, int speed, String driveTrain, String body) {
		super(assetID, assetName);
		this.speed = speed;
		this.driveTrain = driveTrain;
		this.body = body;
	}

	public int getSpeed() {
		return speed;
	}

	public String getDriveTrain() {
		return driveTrain;
	}

	public String getBody() {
		return body;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setDriveTrain(String driveTrain) {
		this.driveTrain = driveTrain;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
