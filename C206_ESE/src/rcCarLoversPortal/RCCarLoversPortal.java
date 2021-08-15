package rcCarLoversPortal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RCCarLoversPortal {

	private static ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
	private static ArrayList<Buyer> buyerList = new ArrayList<Buyer>();
	private static ArrayList<RCCar> RCList = new ArrayList<RCCar>();

	private static final int MAX_MAIN_MENU_OPTION = 5;
	private static final int MAX_SUB_MENU_OPTION = 4;

	public static void main(String[] args) {
		testData();
		RCCarLoversPortal portal = new RCCarLoversPortal();
		portal.mainMenu();
	}

	private void mainMenu() {
		// TODO: Complete main menu
		int option = 0;

		while (option != MAX_MAIN_MENU_OPTION) {
			Helper.line(40, "-");
			System.out.println("RC CAR LOVERS PORTAL");
			Helper.line(40, "-");
			System.out.println("1. RC Cars");
			System.out.println("2. Buyers");
			System.out.println("3. Appointments");
			System.out.println("4. Feedback");
			System.out.println("5. Quit");

			option = Helper.readInt("Enter an option > ");

			// RC Cars sub-menu
			if (option == 1) {
				int optionRCCar = 0;

				while (optionRCCar != MAX_SUB_MENU_OPTION) {
					System.out.println(RCCarLoversPortal.RCCarMenu(RCList));
					optionRCCar = Helper.readInt("Enter an option > ");

					switch (optionRCCar) {
					
					//view
					case 1:
						String assetID = Helper.readString("Asset ID > ");
						String assetName = Helper.readString("Asset Name > ");
						
						boolean foundCar = false;
						for(RCCar i : RCList)
						{
							if (assetID == i.getAssetID() && assetName == i.getAssetName()) {
								viewCar(i);
								foundCar = true;
							}
						}
						if(foundCar != true)
						{
							System.out.println("\nRC Car does not exist");
						}
						break;
					
					//delete
					case 2:

						String assetID1 = Helper.readString("Asset ID > ");

						boolean foundCar1 = false;
						for(RCCar i : RCList)
						{
							if (assetID1 == i.getAssetID()) {
								removeCar(RCList, i);
								foundCar = true;
							}
						}
						if(foundCar1 != true)
						{
							System.out.println("\nRC Car does not exist");
						}

						break;
						
					//add
					case 3:
						
						String AddassetID = Helper.readString("Add Asset ID > ");
						String AddassetName = Helper.readString("Add Asset Name > ");
						int Addspeed = Helper.readInt("Add Speed > ");
						String AdddriveTrain = Helper.readString("Add Drive Train > ");
						String Addbody = Helper.readString("Add Body > ");
						RCCar RCCarToAdd = new RCCar(AddassetID, AddassetName, Addspeed,AdddriveTrain,Addbody);
						addCar(RCList, RCCarToAdd);
						
						break;
						
					//back
					case 4:
						System.out.println("");

						break;

					default:
						System.out.println("\nInvalid option\n");

						break;
					}
				}
				System.out.println("\nComing soon\n");
			}

			else if (option == 2) {
				System.out.println("\nComing soon\n");
			}

			else if (option == 3) {
				System.out.println("\nComing soon\n");
			}

			// Feedback sub-menu
			else if (option == 4) {
				int optionFeedback = 0;

				while (optionFeedback != MAX_SUB_MENU_OPTION) {
					System.out.println(RCCarLoversPortal.feedbackMenu(feedbackList));
					optionFeedback = Helper.readInt("Enter an option > ");

					switch (optionFeedback) {

					case 1:
						int buyerID = Helper.readInt("Buyer ID > ");
						String feedback = Helper.readString("Feedback > ");

						int buyerPosInList = buyerID - 1;

						if (buyerList.size() < buyerID) {
							System.out.println("\nBuyer does not exist");
						}

						else {
							String buyerName = buyerList.get(buyerPosInList).getName();
							int buyerPhoneNo = buyerList.get(buyerPosInList).getPhoneNo();

							Feedback feedbackToAdd = new Feedback(buyerName, buyerPhoneNo, feedback);

							if (RCCarLoversPortal.addFeedback(feedbackList, feedbackToAdd)) {
								System.out.println("\nFeedback added");
							}

							else if (feedbackToAdd.getDescription() == "") {
								System.out.println("\nFeedback body missing");
							}
						}

						break;

					case 2:
						Date dateNow = new Date();
						Feedback feedbackToDelete = null;

						int feedbackID = Helper.readInt("Feedback ID > ");
						int feedbackPosInList = feedbackID - 1;

						if (feedbackList.size() < feedbackID) {
							System.out.println("\nFeedback form does not exist");
						}

						else {
							feedbackToDelete = feedbackList.get(feedbackPosInList);

							if (RCCarLoversPortal.removeFeedback(feedbackList, feedbackToDelete, dateNow)) {
								System.out.println("\nFeedback deleted");
							}

							else if (feedbackList.size() < feedbackPosInList) {
								System.out.println("\nFeedback does not exist");
							}

							else {
								System.out.println("\nThe feedback form is not 3 days old yet");
							}
						}

						break;

					case 3:
						Feedback feedbackToView = null;

						feedbackID = Helper.readInt("Feedback ID > ");
						feedbackPosInList = feedbackID - 1;

						if (feedbackList.size() < feedbackID) {
							System.out.println("\nFeedback does not exist");
						}

						else {
							feedbackToView = feedbackList.get(feedbackPosInList);

							System.out.println(RCCarLoversPortal.viewFeedback(feedbackToView));
						}

						break;

					case 4:
						System.out.println("");

						break;

					default:
						System.out.println("\nInvalid option\n");

						break;
					}
				}
			}

			else if (option == 5) {
				System.out.println("\nExiting application...");
			}
		}
	}

	// ==============================<Temp Test Data>==============================//
	// TODO: Delete when Aqil completes his modules.
	private static void testData() {
		Buyer b1 = new Buyer("Johnny Guitar", 98765432);
		Buyer b2 = new Buyer("Johnny Silverhand", 87654321);
		buyerList.add(b1);
		buyerList.add(b2);
	}

	// ==============================<Feedback>==============================//
	public static boolean addFeedback(ArrayList<Feedback> feedbackList, Feedback fb) {

		if (fb.getName() != "" || fb.getDescription() != "") {
			feedbackList.add(fb);

			return true;
		}

		else {
			return false;
		}
	}

	public static boolean removeFeedback(ArrayList<Feedback> feedbackList, Feedback fb, Date dateNow) {
		boolean isInsideList = false;

		for (Feedback i : feedbackList) {

			if (i.equals(fb)) {
				isInsideList = true;
				break;
			}
		}

		if (isInsideList) {
			long daysPassed = dateNow.getTime() - fb.getDateCreated().getTime();
			int daysPassed3 = 259200000;

			if (daysPassed >= daysPassed3) {
				feedbackList.remove(fb);

				return true;
			}

			else {
				return false;
			}
		}

		else {
			return false;
		}
	}

	public static String viewFeedback(Feedback fb) {
		String feedback = "";
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if (fb != null) {
			feedback += String.format("\n%-22s %-10s %-15s %s\n", "DATE & TIME CREATED", "STATUS", "PHONE NUMBER", "AUTHOR");
			feedback += String.format("%-22s %-10s %-15s %s\n", "-------------------", "------", "------------", "------");
			feedback += String.format("%-22s %-10s %-15s %s\n\n", formatDate.format(fb.getDateCreated()), fb.getStatus(), fb.getPhoneNo(), fb.getName());
			feedback += fb.getDescription();
		}

		return feedback;
	}

	public static String feedbackMenu(ArrayList<Feedback> feedbackList) {
		String feedbackListOutput = "";
		int numberOfIterations = 0;

		feedbackListOutput += "\n----------------------------------------";
		feedbackListOutput += "\nFEEDBACK FORMS";
		feedbackListOutput += "\n----------------------------------------";

		if (!feedbackList.isEmpty()) {

			for (Feedback i : feedbackList) {

				if (numberOfIterations < 1) {
					feedbackListOutput += String.format("\n%-5s %-10s %s", "ID", "STATUS", "FEEDBACK AUTHOR");
					feedbackListOutput += String.format("\n%-5s %-10s %s", "--", "------", "---------------");
					
					numberOfIterations++;
				}

				feedbackListOutput += String.format("\n%-5d %-10s %s", numberOfIterations++, i.getStatus(), i.getName());
			}
		}

		else {
			feedbackListOutput += "\nNothing to display";
		}

		feedbackListOutput += "\n\n1. Add feedback form";
		feedbackListOutput += "\n2. Remove feedback form";
		feedbackListOutput += "\n3. View feedback form";
		feedbackListOutput += "\n4. Update feedback form status";
		feedbackListOutput += "\n5. Back\n";

		return feedbackListOutput;
	}
	
	public static boolean updateFeedbackStatus(Feedback fb, String status) {
	
		if (!fb.getStatus().equals(status)) {
			fb.setStatus(status);
			
			return true;
		}
		
		else {
			return false;
		}
	}

	// YuYang
	// ==============================<RC Cars>==============================//
	public static String CarInfo(RCCar rc) {
		String CarOutput = String.format(
				"Asset ID\n------\n%-10d\n\nAsset Name\n------------\n%-10s\n\nSpeed\n--------\n%-10d\nDrive Train\n--------\n%-10s\nBody\n--------\n%-10s\n",
				rc.getAssetID(), rc.getAssetName(), rc.getSpeed(), rc.getDriveTrain(), rc.getBody());
		return CarOutput;
	}

	public static boolean addCar(ArrayList<RCCar> RCList, RCCar rc) {
		if (rc.getAssetID() != "" || rc.getAssetName() != "") {
			RCList.add(rc);
			return true;
		}

		else {
			return false;
		}
	}

	public static boolean removeCar(ArrayList<RCCar> RCList, RCCar rc) {
		boolean isInsideList = false;

		for (RCCar i : RCList) {

			if (i.equals(rc)) {
				isInsideList = true;
				RCList.remove(i);
				break;
			}
		}
		if (isInsideList != true) {
			return false;
		} else {
			return true;
		}
	}

	public static String viewCar(RCCar rc) {
		String ControlCar = "";

		if (rc != null) {
			ControlCar += "\nAsset ID\n";
			ControlCar += "------\n";
			ControlCar += rc.getAssetID() + "\n\n";
			ControlCar += "Asset Name\n";
			ControlCar += "------------\n";
			ControlCar += rc.getAssetName() + "\n\n";
			ControlCar += "Speed\n";
			ControlCar += "--------\n";
			ControlCar += rc.getSpeed();
			ControlCar += "Drive Train\n";
			ControlCar += "--------\n";
			ControlCar += rc.getDriveTrain();
			ControlCar += "Body\n";
			ControlCar += "--------\n";
			ControlCar += rc.getBody();
		}
		return ControlCar;
	}

	public static String CarList(ArrayList<RCCar> RCList) {
		String CarListOutput = String.format("%-5s %-20s %-5d %-20s %-20s", "ASSET ID", "ASSET NAME", "SPEED",
				"DRIVE TRAIN", "BODY");
		CarListOutput += String.format("\n%-5s %-20s %-5s %-20s %-20s", "--", "---------", "----");
		for (int i = 0; i < RCList.size(); i++) {
			CarListOutput += String.format("\n%-5s %-20s %-5d %-20s %-20s", 1, RCList.get(i).getAssetID(),
					RCList.get(i).getAssetName(), RCList.get(i).getSpeed(), RCList.get(i).getDriveTrain(),
					RCList.get(i).getBody());
		}

		return CarListOutput;
	}
	public static String RCCarMenu(ArrayList<RCCar> RCList) {
		String RCMenuOutput = "";
		int numberOfIterations = 0;

		RCMenuOutput += "\n----------------------------------------";
		RCMenuOutput += "\nFEEDBACK";
		RCMenuOutput += "\n----------------------------------------";

		if (!RCList.isEmpty()) {

			for (RCCar i : RCList) {

				if (numberOfIterations < 1) {
					RCMenuOutput += String.format("\n%-5s %s", "ID", "ASSET ID");
					RCMenuOutput += String.format("\n%-5s %s", "--", "---------------");

					numberOfIterations++;
				}

				RCMenuOutput += String.format("\n%-5d %-10s %s", numberOfIterations++, i.getAssetID(),i.getAssetName());
			}
		}

		else {
			RCMenuOutput += "\nNothing to display";
		}

		RCMenuOutput += "\n\n1. View RC Car";
		RCMenuOutput += "\n2. Remove RC Car";
		RCMenuOutput += "\n3. Add RC Car";
		RCMenuOutput += "\n4. Back\n";

		return RCMenuOutput;
	}
}
