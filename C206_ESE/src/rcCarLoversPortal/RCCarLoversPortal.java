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
}
