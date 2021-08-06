package rcCarLoversPortal;

import java.util.ArrayList;
import java.util.Date;

public class RCCarLoversPortal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world!");
	}
	//==============================<Feedback>==============================//
	public static boolean addFeedback(ArrayList<Feedback> feedbackList, Feedback fb) {
		return true;
	}
	
	public static boolean removeFeedback(ArrayList<Feedback> feedbackList, Feedback fb, Date dateNow) {
		return false;
	}
	
	public static String viewFeedback(Feedback fb) {
		String feedback = "";
		
		return feedback;
	}
	
	public static String feedbackMenu(ArrayList<Feedback> feedbackList) {
		String feedbackListOutput = "";
		
		return feedbackListOutput;
	}
	//YuYang
    // ==============================<RC Cars>==============================//
    public static String CarsMenu(ArrayList<Feedback> RCList) {
        String CarListOutput = "";

 

        return CarListOutput;
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
