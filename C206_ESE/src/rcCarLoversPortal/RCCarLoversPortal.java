package rcCarLoversPortal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RCCarLoversPortal {

	private static ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
	private static ArrayList<Buyer> buyerList = new ArrayList<Buyer>();
	
	public static void main(String[] args) {
		testData();
		RCCarLoversPortal portal = new RCCarLoversPortal();
		portal.mainMenu();
	}
	
	private void mainMenu() {
		// TODO: Complete main menu
		int option = 0;
		
		while (option != 5) {
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
				
				while (optionFeedback != 4) {
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
	
	//==============================<Temp Test Data>==============================//
	// TODO: Delete when Aqil completes his modules.
	private static void testData() {
		Buyer b1 = new Buyer("Johnny Guitar", 98765432);
		Buyer b2 = new Buyer("Johnny Silverhand", 87654321);
		buyerList.add(b1);
		buyerList.add(b2);
	}

	//==============================<Feedback>==============================//
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
		
		if (fb != null) {
			feedback += "\nAUTHOR\n";
			feedback += "------\n";
			feedback += fb.getName() + "\n\n";
			feedback += "PHONE NUMBER\n";
			feedback += "------------\n";
			feedback += fb.getPhoneNo() + "\n\n";
			feedback += "FEEDBACK\n";
			feedback += "--------\n";
			feedback += fb.getDescription();
		}
		
		return feedback;
	}
	
	public static String feedbackMenu(ArrayList<Feedback> feedbackList) {
		String feedbackListOutput = "";
		int numberOfIterations = 0;
	
		feedbackListOutput += "\n----------------------------------------";
		feedbackListOutput += "\nFEEDBACK";
		feedbackListOutput += "\n----------------------------------------";
		
		if (!feedbackList.isEmpty()) {
			
			for (Feedback i : feedbackList) {
				
				if (numberOfIterations < 1) {
					feedbackListOutput += String.format("\n%-5s %s", "ID", "FEEDBACK AUTHOR");
					feedbackListOutput += String.format("\n%-5s %s", "--", "---------------");
					
					numberOfIterations++;
				}
				
				feedbackListOutput += String.format("\n%-5d %s", numberOfIterations++, i.getName());
			}
		}
		
		else {
			feedbackListOutput += "\nNothing to display";
		}
		
		feedbackListOutput += "\n\n1. Add feedback";
		feedbackListOutput += "\n2. Remove feedback";
		feedbackListOutput += "\n3. View feedback";
		feedbackListOutput += "\n4. Back\n";
		
		return feedbackListOutput;
	}
	//YuYang
    // ==============================<RC Cars>==============================//
    public static String CarInfo(RCCar rc) {
        String CarOutput = String.format("Asset ID\n------\n%-10d\n\nAsset Name\n------------\n%-10s\n\nSpeed\n--------\n%-10d\nDrive Train\n--------\n%-10s\nBody\n--------\n%-10s\n", rc.getAssetID(),rc.getAssetName(),rc.getSpeed(),rc.getDriveTrain(),rc.getBody());
        return CarOutput;
    }

    public static boolean addCar(ArrayList<RCCar> RCList, RCCar rc) {
        return true;
    }

    public static boolean removeCar(ArrayList<RCCar> RCList, RCCar rc) {
        return false;
    }

    public static String viewCar(RCCar rc) {
        String ControlCar = "";
        return ControlCar;
    }

    public static String CarListingMenu(ArrayList<RCCar> RCList) {
    	String CarListOutput = String.format("%-5s %-20s %-5d %-20s %-20s", "ASSET ID", "ASSET NAME", "SPEED", "DRIVE TRAIN", "BODY");
        CarListOutput += String.format("\n%-5s %-20s %-5s %-20s %-20s", "--", "---------", "----");
    	for(int i = 0; i < RCList.size(); i++)
    	{
            CarListOutput += String.format("\n%-5s %-20s %-5d %-20s %-20s", 1, RCList.get(i).getAssetID(), RCList.get(i).getAssetName(), RCList.get(i).getSpeed(),RCList.get(i).getDriveTrain(),RCList.get(i).getBody());
    	}
        
        return CarListOutput;
    }
}
