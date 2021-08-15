package rcCarLoversPortalTest;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rcCarLoversPortal.Feedback;
import rcCarLoversPortal.RCCar;
import rcCarLoversPortal.RCCarLoversPortal;

public class RCCarLoversPortalTest {

	// Feedback test variables
	private Feedback fb1;
	private Feedback fb2;
	private Feedback fb3;
	private ArrayList<Feedback> feedbackList;
	private Date dateNow;
	private Date after2Days23Hr59Min;
	private Date after3Days;
	private Calendar add2Days23Hr59Min;
	private Calendar add3Days;

	// Car test variables
	private RCCar rc1;
	private RCCar rc2;
	private RCCar rc3;
	private ArrayList<RCCar> RCList;

	@Before
	public void setUp() throws Exception {
		// Feedback test data
		fb1 = new Feedback("John", 98765432, "I do not like x");
		fb2 = new Feedback("Ken", 87654321, "I do not like y");
		fb3 = new Feedback("", 76543210, "");
		feedbackList = new ArrayList<Feedback>();

		dateNow = new Date();

		add2Days23Hr59Min = Calendar.getInstance();
		add3Days = Calendar.getInstance();

		add2Days23Hr59Min.setTime(dateNow);
		add3Days.setTime(dateNow);

		add2Days23Hr59Min.add(Calendar.DAY_OF_MONTH, 2);
		add2Days23Hr59Min.add(Calendar.HOUR, 23);
		add2Days23Hr59Min.add(Calendar.MINUTE, 59);

		add3Days.add(Calendar.DAY_OF_MONTH, 3);

		after2Days23Hr59Min = add2Days23Hr59Min.getTime();
		after3Days = add3Days.getTime();
	}

	// ==============================<Feedback>==============================//
	@Test
	public void addFeedbackTest() {
		// check if list exists
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);

		// check if feedback can be added
		feedbackList.add(fb1);

		assertEquals("Check that feedbackList's size is 1", 1, feedbackList.size());
		assertSame("Check the correct Feedback is added", fb1, feedbackList.get(0));

		// check if a second feedback form can be added
		feedbackList.add(fb2);

		assertEquals("Check that feedbackList's size is 2", 2, feedbackList.size());
		assertSame("Check the correct Feedback is added", fb2, feedbackList.get(1));

		// check if invalid feedback form is not added
		boolean isAdded = RCCarLoversPortal.addFeedback(feedbackList, fb3);

		assertFalse("Check if empty Feedback is added", isAdded);
		assertEquals("Check if empty Feedback is NOT added to feedbackList", 2, feedbackList.size());
	}

	@Test
	public void removeFeedbackTest() {
		// check if list exists
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);

		// check if feedback which does not exist cannot be removed
		boolean isRemoved = RCCarLoversPortal.removeFeedback(feedbackList, fb3, dateNow);

		assertFalse("Check if feedback which does not exist cannot be removed", isRemoved);

		// check if feedback that is 2 days, 23 hours, and 59 minutes old cannot be
		// removed
		feedbackList.add(fb1);

		isRemoved = RCCarLoversPortal.removeFeedback(feedbackList, fb1, after2Days23Hr59Min);

		assertFalse("Check if feedback cannot be deleted after 2 days and 59 minutes", isRemoved);
		assertNotNull("Check if feedback still exists", feedbackList.get(0));

		// check if feedback that is 3 days old can be removed
		isRemoved = RCCarLoversPortal.removeFeedback(feedbackList, fb1, after3Days);

		assertTrue("Check if feedback can be deleted after 3 days", isRemoved);
		assertTrue("Check if feedback is deleted", feedbackList.isEmpty());
	}

	@Test
	public void viewFeedbackTest() {
		// check if list exists
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);

		// check if feedback is displayed properly
		feedbackList.add(fb1);
		String expected = "";
		String actual = RCCarLoversPortal.viewFeedback(feedbackList.get(0));
		
		// date format
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		expected += String.format("\n%-22s %-15s %s\n", "DATE & TIME CREATED", "PHONE NUMBER", "AUTHOR");
		expected += String.format("%-22s %-15s %s\n", "-------------------", "------------", "------");
		expected += String.format("%-22s %-15s %s\n\n", formatDate.format(fb1.getDateCreated()), fb1.getPhoneNo(), fb1.getName());
		expected += fb1.getDescription();

		assertEquals("Check if the feedback is displayed properly", expected, actual);

		// check if feedback that does not exist is not displayed
		feedbackList.remove(fb1);
		feedbackList.add(null);

		expected = "";
		actual = RCCarLoversPortal.viewFeedback(feedbackList.get(0));

		assertEquals("Check if feeback that no longer exists is not displayed", expected, actual);
	}

	@Test
	public void feedbackMenuTest() {
		// check if list exists
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);

		// check if list is empty, a relevant message will be displayed
		String expected = "\n----------------------------------------";
		expected += "\nFEEDBACK FORMS";
		expected += "\n----------------------------------------";
		expected += "\nNothing to display";
		expected += "\n\n1. Add feedback form";
		expected += "\n2. Remove feedback form";
		expected += "\n3. View feedback form";
		expected += "\n4. Back\n";
		
		String actual = RCCarLoversPortal.feedbackMenu(feedbackList);

		assertEquals("Check if feedback list is empty, a relevant message will be displayed", expected, actual);

		// check if list is displayed properly
		feedbackList.add(fb1);
		feedbackList.add(fb2);

		expected = "\n----------------------------------------";
		expected += "\nFEEDBACK FORMS";
		expected += "\n----------------------------------------";
		expected += String.format("\n%-5s %s", "ID", "FEEDBACK AUTHOR");
		expected += String.format("\n%-5s %s", "--", "---------------");
		expected += String.format("\n%-5d %s", 1, fb1.getName());
		expected += String.format("\n%-5d %s", 2, fb2.getName());
		expected += "\n\n1. Add feedback form";
		expected += "\n2. Remove feedback form";
		expected += "\n3. View feedback form";
		expected += "\n4. Back\n";
		
		actual = RCCarLoversPortal.feedbackMenu(feedbackList);

		assertEquals("Check if all feedback in the feedback list is displayed", expected, actual);
		
		// check if first feedback is removed, the second feedback in the list does not retain its old ID
		feedbackList.remove(fb1);
		
		expected = "\n----------------------------------------";
		expected += "\nFEEDBACK FORMS";
		expected += "\n----------------------------------------";
		expected += String.format("\n%-5s %s", "ID", "FEEDBACK AUTHOR");
		expected += String.format("\n%-5s %s", "--", "---------------");
		expected += String.format("\n%-5d %s", 2, fb2.getName());
		expected += "\n\n1. Add feedback form";
		expected += "\n2. Remove feedback form";
		expected += "\n3. View feedback form";
		expected += "\n4. Back\n";
	
		actual = RCCarLoversPortal.feedbackMenu(feedbackList);
		
		assertNotEquals("Check if first feedback is removed from the feedback list, the second feedback does not retain its old ID", expected, actual);
	}
	
	@Test
	public void updateFeedbackStatusTest() {
		// check if list exists
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);
		
		// check if status of feedback can be updated and that the change is reflected in the feedback list
		feedbackList.add(fb1);
		boolean isChanged = RCCarLoversPortal.updateFeedbackStatus(fb1, "SOLVED");
		
		assertTrue("Check if the status of feedback can be updated", isChanged);
		assertEquals("Check if the feedback's status is correct", "SOLVED", feedbackList.get(0).getStatus());
		
		// check if feedback's current status is PENDING and the updated status is also PENDING, no updates will be performed
		feedbackList.get(0).setStatus("PENDING");
		isChanged = RCCarLoversPortal.updateFeedbackStatus(feedbackList.get(0), "PEDNING");
		
		assertFalse("Check if the status of feedback is not updated", isChanged);
		
		// check if feedback's current status is SOLVED and the updated status is also SOLVED, no updates will be performed
		feedbackList.get(0).setStatus("SOLVED");
		isChanged = RCCarLoversPortal.updateFeedbackStatus(feedbackList.get(0), "SOLVED");
		
		assertFalse("Check if the status of feedback is not updated", isChanged);	
	}

	// YuYang
	// ==============================<RC Cars>==============================//
	@Test
	public void addCarTest() {
		// check if list exists
		assertNotNull("Check if there is a valid ArrayList to add to", RCList);

		// check if Car can be added
		RCCarLoversPortal.addCar(RCList, rc1);

		assertEquals("Check that RCList's size is 1", 1, RCList.size());
		assertSame("Check the correct Feedback is added", rc1, RCList.get(0));

		// check if a second Car can be added
		RCCarLoversPortal.addCar(RCList, rc2);
		assertEquals("Check that RCList's size is 2", 2, RCList.size());
		assertSame("Check the correct Control Car is added", rc2, RCList.get(1));

		// check if invalid Car is not added
		boolean isAdded = RCCarLoversPortal.addCar(RCList, rc3);

		assertFalse("Check if empty RCCar is added", isAdded);
		assertEquals("Check if empty RCCar is NOT added to RCList", 2, RCList.size());
	}

	@Test
	public void removeCarTest() {
		// check if list exists
		assertNotNull("Check if there is a valid ArrayList to add to", RCList);

		// check if Car which does not exist cannot be removed
		boolean isRemoved = RCCarLoversPortal.removeCar(RCList, rc3);

		assertFalse("Check if Car which does not exist cannot be removed", isRemoved);

		// check if 2nd Car can be removed
		RCCarLoversPortal.removeCar(RCList, rc2);

		assertEquals("Check that RCList's size is 1", 1, RCList.size());

		// check if the last Car can be removed
		RCCarLoversPortal.removeCar(RCList, rc1);

		assertEquals("Check that RCList's size is 0", 0, RCList.size());

		// check if the last Car that was removed cannot be removed again
		boolean isRemoved1 = RCCarLoversPortal.removeCar(RCList, rc1);

		assertFalse("Check if Car which was removed cannot be removed again", isRemoved1);

	}

	@Test
	public void viewCarTest() {
		// check if list exists
		assertNotNull("Check if there is a valid ArrayList to add to", RCList);

		// check if car is displayed properly
		String expected = "";
		String actual = RCCarLoversPortal.CarInfo(rc1);

		expected += "Asset ID\n";
		expected += "------\n";
		expected += rc1.getAssetID() + "\n\n";
		expected += "Asset Name\n";
		expected += "------------\n";
		expected += rc1.getAssetName() + "\n\n";
		expected += "Speed\n";
		expected += "--------\n";
		expected += rc1.getSpeed() + "\n";
		expected += "Drive Train\n";
		expected += "--------\n";
		expected += rc1.getDriveTrain() + "\n";
		expected += "Body\n";
		expected += "--------\n";
		expected += rc1.getBody() + "\n";

		assertEquals("Check if the car is displayed properly", expected, actual);

		// check if car that does not exist is not displayed
		RCList.add(rc1);
		RCList.remove(rc1);
		RCList.add(null);

		expected = "";
		actual = RCCarLoversPortal.CarInfo(RCList.get(0));

		assertEquals("Check if car that no longer exists is not displayed", expected, actual);
	}

	@Test
	public void CarListTest() {
		// check if list exists
		assertNotNull("Check if there is a valid ArrayList to add to", RCList);

		// check if list is empty, a relevant messsage will be displayed
		String expected = "Nothing to display";
		String actual = RCCarLoversPortal.CarList(RCList);

		assertEquals("Check if Car list is empty, a relevant message will be displayed", expected, actual);

		// check if list is displayed properly
		RCCarLoversPortal.addCar(RCList, rc1);
		RCCarLoversPortal.addCar(RCList, rc2);

		//public RCCar(String assetID, String assetName, int speed, String driveTrain, String body) {
		expected += String.format("%-5s %-20s %-5d %-20s %-20s", "ASSET ID", "ASSET NAME", "SPEED", "DRIVE TRAIN", "BODY");
		expected += String.format("\n%-5s %-20s %-5s %-20s %-20s", "--", "---------", "----");
		expected += String.format("\n%-5s %-20s %-5d %-20s %-20s", 1, rc1.getAssetID(), rc1.getAssetName(), rc1.getSpeed(),rc1.getDriveTrain(),rc1.getBody());
		expected += String.format("\n%-5s %-20s %-5d %-20s %-20s", 2, rc2.getAssetID(), rc2.getAssetName(), rc2.getSpeed(),rc2.getDriveTrain(),rc2.getBody());
		actual = RCCarLoversPortal.CarList(RCList);

		assertEquals("Check if all Cars in the RCCar list is displayed", expected, actual);
	}
	
	@After
	public void tearDown() throws Exception {
		// Feedback test data
		fb1 = null;
		fb2 = null;
		fb3 = null;
		feedbackList = null;

		dateNow = null;

		after2Days23Hr59Min = null;
		after3Days = null;

		add2Days23Hr59Min = null;
		add3Days = null;
		
		//Car test data
		rc1 = null;
		rc2 = null;
		rc3 = null;
		RCList = null;
	}
}
