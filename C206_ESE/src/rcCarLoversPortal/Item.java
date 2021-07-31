package rcCarLoversPortal;

public abstract class Item {
	
	private String assetID;
	private String assetName;
	
	public Item(String assetID, String assetName) {
		this.assetID = assetID;
		this.assetName = assetName;
	}

	public String getAssetID() {
		return assetID;
	}

	public String getAssetName() {
		return assetName;
	}
	
	public void setAssetID(String assetID) {
		this.assetID = assetID;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
}
