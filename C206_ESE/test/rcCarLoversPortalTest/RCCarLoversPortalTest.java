package rcCarLoversPortalTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rcCarLoversPortal.Feedback;
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
	
	@Before
	public void setUp() throws Exception {
		// Feedback test data
		fb1 = new Feedback("John",98765432,"I do not like x");
		fb2 = new Feedback("Ken",87654321,"I do not like y");
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
	//==============================<Feedback>==============================//
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
		
		assertFalse("Check if empty Feedback is added",isAdded);
		assertEquals("Check if empty Feedback is NOT added to feedbackList", 2, feedbackList.size());
	}
	
	@Test
	public void removeFeedbackTest() {
		// check if list exists
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);
		
		// check if feedback which does not exist cannot be removed
		boolean isRemoved = RCCarLoversPortal.removeFeedback(feedbackList, fb3, dateNow);
		
		assertFalse("Check if feedback which does not exist cannot be removed", isRemoved);
		
		// check if feedback that is 2 days, 23 hours, and 59 minutes old cannot be removed
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
		
		expected += "AUTHOR\n";
		expected += "------\n";
		expected += fb1.getName() + "\n\n";
		expected += "PHONE NUMBER\n";
		expected += "------------\n";
		expected += fb1.getPhoneNo() + "\n\n";
		expected += "FEEDBACK\n";
		expected += "--------\n";
		expected += fb1.getDescription() + "\n";
		
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

		// check if list is empty, a relevant messsage will be displayed
		String expected = "Nothing to display";
		String actual = RCCarLoversPortal.feedbackMenu(feedbackList);
		
		assertEquals("Check if feedback list is empty, a relevant message will be displayed", expected, actual);
		
		// check if list is displayed properly
		feedbackList.add(fb1);
		feedbackList.add(fb2);
		
		expected += String.format("%-5s %-10s %s", "ID", "PHONE NO.", "NAME");
		expected += String.format("\n%-5s %-10s %s", "--", "---------", "----");
		expected += String.format("\n%-5d %-10d %s", 1, fb1.getPhoneNo(), fb1.getName());
		expected += String.format("\n%-5d %-10d %s", 2, fb2.getPhoneNo(), fb2.getName());
		actual = RCCarLoversPortal.feedbackMenu(feedbackList);
		
		assertEquals("Check if all feedback in the feedback list is displayed", expected, actual);
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
	}
}
