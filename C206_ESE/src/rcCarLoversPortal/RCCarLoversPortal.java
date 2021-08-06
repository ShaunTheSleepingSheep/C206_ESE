package rcCarLoversPortal;

import java.util.ArrayList;
import java.util.Date;

public class RCCarLoversPortal {

	private ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
			feedback += "AUTHOR\n";
			feedback += "------\n";
			feedback += fb.getName() + "\n\n";
			feedback += "PHONE NUMBER\n";
			feedback += "------------\n";
			feedback += fb.getPhoneNo() + "\n\n";
			feedback += "FEEDBACK\n";
			feedback += "--------\n";
			feedback += fb.getDescription() + "\n";
		}
		
		return feedback;
	}
	
	public static String feedbackMenu(ArrayList<Feedback> feedbackList) {
		String feedbackListOutput = "";
		
		return feedbackListOutput;
	}
	//YuYang
    // ==============================<RC Cars>==============================//
    public static String CarInfo(RCCar rc) {
        String CarOutput = "";
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
        String CarListOutput = "";
        return CarListOutput;
    }
}
