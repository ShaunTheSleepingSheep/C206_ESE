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
		
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);
		
		feedbackList.add(fb1);
		assertEquals("Check that feedbackList's size is 1", 1, feedbackList.size());
		assertSame("Check the correct Feedback is added", fb1, feedbackList.get(0));
		
		feedbackList.add(fb2);
		assertEquals("Check that feedbackList's size is 2", 2, feedbackList.size());
		assertSame("Check the correct Feedback is added", fb2, feedbackList.get(1));
		
		boolean isAdded = RCCarLoversPortal.addFeedback(feedbackList, fb3);
		assertFalse("Check if empty Feedback is added",isAdded);
		assertEquals("Check if empty Feedback is NOT added to feedbackList", 2, feedbackList.size());
	}
	
	@Test
	public void removeFeedbackTest() {
		
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);
		
		boolean isRemoved = RCCarLoversPortal.removeFeedback(feedbackList, fb3, dateNow);
		assertFalse("Check if feedback which does not exist cannot be removed", isRemoved);
		
		feedbackList.add(fb1);
		isRemoved = RCCarLoversPortal.removeFeedback(feedbackList, fb1, after2Days23Hr59Min);
		assertFalse("Check if feedback cannot be deleted after 2 days and 59 minutes", isRemoved);
		assertNotNull("Check if feedback still exists", feedbackList.get(0));
		
		isRemoved = RCCarLoversPortal.removeFeedback(feedbackList, fb1, after3Days);
		assertTrue("Check if feedback can be deleted after 3 days", isRemoved);
		assertTrue("Check if feedback is deleted", feedbackList.isEmpty());
	}
	
	@Test
	public void viewFeedbackTest() {
		
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);
		
		feedbackList.add(fb1);
		// TODO: Design expected output
		String expected = "output";
		String actual = RCCarLoversPortal.viewFeedback(feedbackList.get(0));
		assertEquals("Check if the feedback is displayed properly", expected, actual);
	
		feedbackList.remove(fb1);
		feedbackList.add(null);
		expected = "";
		actual = RCCarLoversPortal.viewFeedback(feedbackList.get(0));
		assertEquals("Check if feeback that no longer exists is not displayed", expected, actual);
	}
	
	@Test
	public void feedbackMenuTest() {
		
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);

		String expected = "Nothing to display";
		String actual = RCCarLoversPortal.feedbackMenu(feedbackList);
		assertEquals("Check if feedback list is empty, a relevant message will be displayed", expected, actual);
		
		feedbackList.add(fb1);
		feedbackList.add(fb2);
		// TODO: Design expected output
		expected = "output";
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
